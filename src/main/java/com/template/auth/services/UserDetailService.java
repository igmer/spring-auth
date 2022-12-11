package com.template.auth.services;

import com.template.auth.exceptions.RequestException;
import com.template.auth.model.MyUserPrincipal;
import com.template.auth.model.User;
import com.template.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new RequestException(HttpStatus.NOT_FOUND,"P-404","Bad Credentials.!");
        }
        return new MyUserPrincipal(user.get());
    }
    public MyUserPrincipal loadUserByUsername(String username,String password)  {
        Optional<User> user = userRepository.findByUsername(username);
        //user was found, now we need compare password
        if (user.isEmpty()){
            throw new RequestException(HttpStatus.NOT_FOUND,"P-404","Bad Credentials.!");
        }
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        boolean passChecker = bc.matches(password,user.get().getPassword());
        if (!passChecker){
            throw new RequestException(HttpStatus.NOT_FOUND,"P-404","Bad Credentials.!");
        }
        return new MyUserPrincipal(user.get());
    }
}
