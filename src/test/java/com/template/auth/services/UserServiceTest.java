package com.template.auth.services;

import com.template.auth.model.User;
import com.template.auth.repository.UserRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserServiceTest {
//    @Autowired
//    UserRepository userRepository;

    @Test
    void findByUsername() {
//        User user = userRepository.findByUsername("igmerdz@gmail.com").get();
//        assertEquals("igmerdz@gmail.com",user.getEmail());
    }

    @Test
    void save() {
    }
}