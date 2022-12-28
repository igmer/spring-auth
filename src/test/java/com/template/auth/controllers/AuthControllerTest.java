package com.template.auth.controllers;

import com.template.auth.dto.LoginRequest;
import com.template.auth.dto.TokenResponse;
import com.template.auth.exceptions.RequestException;
import com.template.auth.security.JwtUtils;
import com.template.auth.services.UserDetailService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthController authController = new AuthController(jwtUtils,userDetailService);

    @Test
    void authenticate() {
        ResponseEntity<TokenResponse> response = authController.authenticate(LoginRequest.builder().username("fernandorod").password("12345678").build());
        assertNotNull(response);
        assertEquals(200,response.getStatusCode().value());

    }

}