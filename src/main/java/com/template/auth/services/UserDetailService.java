package com.template.auth.services;

import com.template.auth.exceptions.RequestException;
import com.template.auth.model.MyUserPrincipal;
import com.template.auth.model.Role;
import com.template.auth.model.User;
import com.template.auth.repository.RoleRepository;
import com.template.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username)  {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new RequestException(HttpStatus.NOT_FOUND,"P-404","Bad Credentials.!");
        }
        return new MyUserPrincipal(user.get());
    }
    public MyUserPrincipal loadUserByUsername(String username,String password)  {
        Optional<User> userOptional = userRepository.findByUsername(username);
        //user was found, now we need compare password
        if (userOptional.isEmpty()){
            throw new RequestException(HttpStatus.UNAUTHORIZED,"P-401","Bad Credentials.!");
        }
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        boolean passChecker = bc.matches(password,userOptional.get().getPassword());
        if (!passChecker){
            throw new RequestException(HttpStatus.UNAUTHORIZED,"P-401","Bad Credentials.!");
        }
        User user = userOptional.get();
        List<Role> roles = roleRepository.findAllByUser(user.getId());
        user.setRoles(roles);
        return new MyUserPrincipal(userOptional.get());
    }
}
