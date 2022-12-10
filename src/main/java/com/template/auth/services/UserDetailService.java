package com.template.auth.services;

import com.template.auth.exceptions.RequestException;
import com.template.auth.model.MyUserPrincipal;
import com.template.auth.model.User;
import com.template.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        Optional<User> user = userRepository.findByUsername(username); //TODO add password  validation
        if (user.isEmpty()){
            throw new RequestException(HttpStatus.NOT_FOUND,"P-404","Bad Credentials.!");
        }
        return new MyUserPrincipal(user.get());
    }
}
