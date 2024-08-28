package com.api.auth.controller;

import com.api.auth.dto.AuthDTO;
import com.api.auth.dto.NewUserDTO;
import com.api.auth.dto.RequestDTO;
import com.api.auth.dto.TokenDTO;
import com.api.auth.model.AuthUser;
import com.api.auth.service.IAuthService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthDTO authDTO) {
        TokenDTO token = this.iAuthService.login(authDTO);
        if(token==null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(token);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDTO> validate(@RequestParam String token, @RequestBody RequestDTO requestDTO) {
        TokenDTO tokenDTO = this.iAuthService.validate(token, requestDTO);
        if(tokenDTO==null) {

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDTO newUserDTO) {
        AuthUser authUser = this.iAuthService.save(newUserDTO);
        if(authUser==null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(authUser);
    }
}
