package com.api.auth.service;

import com.api.auth.dto.AuthDTO;
import com.api.auth.dto.NewUserDTO;
import com.api.auth.dto.RequestDTO;
import com.api.auth.dto.TokenDTO;
import com.api.auth.model.AuthUser;

public interface IAuthService {

    AuthUser save(NewUserDTO newUserDTO);

    TokenDTO login(AuthDTO authDTO);

    TokenDTO validate(String token, RequestDTO requestDTO);
}
