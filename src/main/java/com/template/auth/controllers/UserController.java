package com.template.auth.controllers;


import com.template.auth.dto.UserRequestDto;
import com.template.auth.exceptions.RequestException;
import com.template.auth.model.User;
import com.template.auth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/by-username")
    public ResponseEntity<User> findByUsername(@RequestParam(name = "username") String username) {
        Optional<User> user = this.userService.findByUsername(username);
        if (user.isEmpty()){
            throw  new RequestException(HttpStatus.NOT_FOUND,"P-400","User not found");
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@Valid @RequestBody UserRequestDto user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPass);
        userService.save(user.toUser());
        return new ResponseEntity<User>(user.toUser(), HttpStatus.OK);

    }
}
