package com.ms.user.ms_user.controller;

import com.ms.user.ms_user.controller.doc.UserDoc;
import com.ms.user.ms_user.dto.UserDTO;
import com.ms.user.ms_user.model.UserEntity;
import com.ms.user.ms_user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class UserController implements UserDoc {
    
    private final IUserService iUserService;
    
    @Override
    public ResponseEntity<UserEntity> create(UserDTO userDTO) {
        
        return this.iUserService.save(userDTO);
    }

    @Override
    public ResponseEntity<UserEntity> createForm2(UserEntity userEntity) {
        return ResponseEntity
                .ok(
                        userEntity
                );
    }

    @Override
    public ResponseEntity<UserEntity> getById(String id) {
        return this.iUserService.getById(id);
    }

    @Override
    public ResponseEntity<List<UserEntity>> listUsers() {
        return this.iUserService.getAll();
    }

    @Override
    public ResponseEntity<UserEntity> getByDocument(String document) {
        return this.iUserService.getByDocument(document);
    }

    @Override
    public ResponseEntity<UserEntity> deleteById(String id) {
        return this.iUserService.deleteUser(id);
    }

    @Override
    public ResponseEntity<UserEntity> updateById(String id, UserDTO userDTO) {
        return this.iUserService.updateUserById(id, userDTO);
    }

    @Override
    public ResponseEntity<UserEntity> updateByDocument(String document, UserDTO userDTO) {
        return this.iUserService.updateUserByDocument(document, userDTO);
    }

    @Override
    public ResponseEntity<?> getReviewByUserId(String id) {
        return this.iUserService.getReviewByUserId(id);
    }
}
