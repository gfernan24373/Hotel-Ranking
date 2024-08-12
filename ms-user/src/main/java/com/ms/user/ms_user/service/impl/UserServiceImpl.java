package com.ms.user.ms_user.service.impl;

import com.ms.user.ms_user.configs.IHotelServiceFeing;
import com.ms.user.ms_user.dto.HotelDTO;
import com.ms.user.ms_user.dto.RankingDTO;
import com.ms.user.ms_user.dto.UserDTO;
import com.ms.user.ms_user.dto.UserRankingsDTO;
import com.ms.user.ms_user.dto.jms.JmsEmailDTO;
import com.ms.user.ms_user.exception.MyHandleException;
import com.ms.user.ms_user.model.UserEntity;
import com.ms.user.ms_user.producer.IMsEmailProducer;
import com.ms.user.ms_user.repository.UserRepository;
import com.ms.user.ms_user.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Setter
@Slf4j
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final IHotelServiceFeing iHotelServiceFeing;
    private final IMsEmailProducer iMsEmailProducer;

    @Override
    public ResponseEntity<UserEntity> save(UserDTO userDTO) {
        var currentUser = this.userRepository.findByDocument(userDTO.getDocument());
        if(currentUser.isPresent()) {
            throw new MyHandleException("User already exist in db");
        }
        UserEntity user = UserEntity
                .builder()
                .id(UUID.randomUUID().toString())
                .document(userDTO.getDocument())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .information(userDTO.getInformation())
                .build();
        var newUser = this.userRepository.save(user);

        JmsEmailDTO jmsEmailDTO = new JmsEmailDTO();

        jmsEmailDTO.setReceiver(newUser.getEmail());
        jmsEmailDTO.setSubject("Welcome to the family");
        jmsEmailDTO.setMessageBody("Registration successfully");
        iMsEmailProducer.generateTransactionEmail(jmsEmailDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @Override
    public ResponseEntity<UserEntity> updateUserById(String id, UserDTO userDTO) {
        //TODO validaciones si el usuario no existe
        var userI = this.userRepository.findById(id);
        if(userI.isPresent()) {

            var currentUser = userI.get();
            currentUser.setDocument(userDTO.getDocument());
            currentUser.setName(userDTO.getName());
            currentUser.setEmail(userDTO.getEmail());
            currentUser.setInformation(userDTO.getInformation());

            var updateUser = this.userRepository.save(currentUser);

            return ResponseEntity.ok(updateUser);

        }

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new UserEntity());
    }

    @Override
    public ResponseEntity<UserEntity> updateUserByDocument(String document, UserDTO userDTO) {

        var userD = this.userRepository.findByDocument(document)
                .orElseThrow(() -> new MyHandleException("User does not exist"));

        userD.setDocument(userDTO.getDocument());
        userD.setName(userDTO.getName());
        userD.setEmail(userDTO.getEmail());
        userD.setInformation(userDTO.getInformation());

        var updateUser = this.userRepository.save(userD);

        return ResponseEntity.ok(updateUser);
    }

    @Override
    public ResponseEntity<UserEntity> deleteUser(String id) {
        this.userRepository.deleteById(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @Override
    public ResponseEntity<List<UserEntity>> getAll() {
        var users = this.userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserEntity> getById(String id) {
        var user = this.userRepository
                .findById(id)
                .orElse(new UserEntity());
        return ResponseEntity
                .ok(user);
    }

    @Override
    public ResponseEntity<UserEntity> getByDocument(String document) {
        var userDoc = this.userRepository
                .findByDocument(document)
                .orElse(new UserEntity());
        return ResponseEntity.ok(userDoc);
    }

    @Override
    public ResponseEntity<?> getReviewByUserId(String id) {
        UserRankingsDTO userRankingsDTO = this
                .userRepository
                .findById(id)
                .map(user -> UserRankingsDTO
                                .builder()
                                .id(user.getId())
                                .name(user.getName())
                                .document(user.getDocument())
                                .email(user.getEmail())
                                .information(user.getInformation())
                                .build()
                ).orElseThrow(()-> new MyHandleException("User does not exist"));

        RankingDTO[] rankings = this.restTemplate.getForObject("http://localhost:8083/api/v1/ranking/" + id, RankingDTO[].class);

        if(rankings != null){
            var listRanking = Arrays.stream(rankings).toList();
            var rankinFull = listRanking.stream()
                    .peek(ran -> {
//                        var hotelResponse = this.restTemplate.getForObject(
//                                "http://localhost:8080/api/v1/hotel/" + ran.getHotelId(),
//                                HotelDTO.class
                        var uuid = UUID.fromString(ran.getHotelId());
                        var hotelResponse = this.iHotelServiceFeing.getHotelById(uuid);
                        ran.setHotel(hotelResponse);

                    })
                    .collect(Collectors.toList());

            userRankingsDTO.setRankings(rankinFull);
        }


        return ResponseEntity.ok(userRankingsDTO);
    }

}
