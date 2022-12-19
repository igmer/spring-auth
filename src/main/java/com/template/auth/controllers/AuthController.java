package com.template.auth.controllers;

import com.template.auth.dto.LoginRequest;
import com.template.auth.dto.TokenResponse;
import com.template.auth.exceptions.RequestException;
import com.template.auth.model.MyUserPrincipal;
import com.template.auth.security.JwtUtils;
import com.template.auth.services.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtils jwtUtils;
    private final  UserDetailService userDetailService;
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody LoginRequest loginRequest){
        final MyUserPrincipal userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername(), loginRequest.getPassword());
        if (userDetails != null) {
            //TODO add refresh token
            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        }

        throw new RequestException(HttpStatus.UNAUTHORIZED,"P-401","bad credentials");
    }
}
