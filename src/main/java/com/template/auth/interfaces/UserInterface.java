package com.template.auth.interfaces;

import com.template.auth.model.User;
import org.springframework.http.ResponseEntity;

public interface UserInterface {
    ResponseEntity<?> findByUsername(String username);
    ResponseEntity<?> create(User user);
}
