package com.template.auth.controllers;


import com.sun.istack.NotNull;
import com.template.auth.dto.Message;
import com.template.auth.exceptions.RequestException;
import com.template.auth.interfaces.UserInterface;
import com.template.auth.model.User;
import com.template.auth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController implements UserInterface {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/by-username")
    @Override
    public ResponseEntity<?> findByUsername(@RequestParam(name = "username") String username) {
        Optional<User> user = this.userService.findByUsername(username);
        if (user.isEmpty()){
            throw  new RequestException(HttpStatus.NOT_FOUND,"P-400","User not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    @Override
    public ResponseEntity<?> create(@NotNull @RequestBody  User user) {

        if (user == null){
            throw new RequestException(HttpStatus.BAD_REQUEST,"P-400","user is required");

        }
        User newUser = userService.create(user);
        if (newUser == null){
            throw new RequestException(HttpStatus.BAD_REQUEST,"P-500","cannot create user");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);

    }
}