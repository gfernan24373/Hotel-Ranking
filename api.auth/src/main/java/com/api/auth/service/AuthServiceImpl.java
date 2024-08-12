package com.api.auth.service;

import com.api.auth.dto.AuthDTO;
import com.api.auth.dto.NewUserDTO;
import com.api.auth.dto.RequestDTO;
import com.api.auth.dto.TokenDTO;
import com.api.auth.model.AuthUser;
import com.api.auth.repository.AuthRepository;
import com.api.auth.security.JwtProvider;
import com.api.auth.security.PasswordEncoderConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements IAuthService{

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public AuthUser save(NewUserDTO newUserDTO) {
        Optional<AuthUser> user = this
                .authRepository
                .findByUserName((newUserDTO.getUserName()));

        if (user.isPresent()) {
            return null;
        }

        String password = this
                .passwordEncoder
                .encode(newUserDTO.getPassword());

        AuthUser authUser = AuthUser
                .builder()
                .userName(newUserDTO.getUserName())
                .password(newUserDTO.getRole())
                .build();


        return this.authRepository.save((authUser));

    }

    @Override
    public TokenDTO login(AuthDTO authDTO) {
        Optional<AuthUser> user = this
                .authRepository
                .findByUserName(authDTO.getUsername());

        if (user.isEmpty()) {
            return null;
        }

        if(this.passwordEncoder.matches(authDTO.getPassword(),user.get().getPassword())) {
            return new TokenDTO(this.jwtProvider.createToken(user.get()));
        }
        return null;
    }

    @Override
    public TokenDTO validate(String token, RequestDTO requestDTO) {

        if(this.jwtProvider.validate(token, requestDTO)) {
            return null;
        }
        String username = this.jwtProvider.getUsernameFromToken(token);

        if(!this.authRepository.findByUserName(username).isPresent()) {
            return null;
        }
        return new TokenDTO(token);
    }
}
