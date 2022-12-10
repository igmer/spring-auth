package com.template.auth.controllers;

import com.template.auth.dto.LoginRequest;
import com.template.auth.exceptions.RequestException;
import com.template.auth.security.JwtUtils;
import com.template.auth.services.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final  UserDetailService userDetailService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginRequest loginRequest){

        final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername());
        if (userDetails != null) {
            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        }
        throw new RequestException(HttpStatus.NOT_FOUND,"P-404","bad credentials");
    }
}
