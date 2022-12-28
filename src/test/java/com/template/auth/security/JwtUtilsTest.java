package com.template.auth.security;

import com.template.auth.dto.LoginRequest;
import com.template.auth.model.MyUserPrincipal;
import com.template.auth.model.User;
import com.template.auth.services.UserDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JwtUtilsTest {

    @Autowired
    JwtUtils jwtUtils = new JwtUtils();
    @Autowired
    UserDetailService userDetailService;

    @Test
    void generateToken() {
        Map<String,Object> claims = new HashMap<>();
        final MyUserPrincipal userDetails = userDetailService.loadUserByUsername("fernandorod", "12345678");
        assertNotNull(userDetails);
        String token = jwtUtils.createToken(claims,userDetails);
        assertNotNull(token);
            }


}