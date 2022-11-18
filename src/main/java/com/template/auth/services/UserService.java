package com.template.auth.services;

import com.template.auth.interfaces.UserInterface;
import com.template.auth.model.User;
import com.template.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public User save(User user){
        return userRepository.save(user);
    }

}
