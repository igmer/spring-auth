package com.template.auth.services;

import com.template.auth.exceptions.RequestException;
import com.template.auth.model.Group;
import com.template.auth.model.User;
import com.template.auth.repository.GroupRepository;
import com.template.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public User save(User user){
        Optional<User> prevUser = userRepository.findByUsername(user.getUsername());
        if (prevUser.isPresent()){
            throw new RequestException(HttpStatus.CONFLICT,"P-409","User already exits");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setGroups(user.getGroups()
                .stream()
                .map(group -> {
                    Group ggroup = group;
                    if (group.getId() > 0){
                        ggroup = groupRepository.findById(ggroup.getId()).get();
                    }
                    ggroup.addUser(newUser);
                    return ggroup;

                }).collect(Collectors.toSet()));
        return userRepository.save(newUser);
    }

}
