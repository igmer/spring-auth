package com.template.auth.services;

import com.template.auth.model.Role;
import com.template.auth.model.User;
import com.template.auth.repository.RoleRepository;
import com.template.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Optional<Role> findByname(String role){
        return roleRepository.findByName(role);
    }
    public Role save(Role role){
        return roleRepository.save(role);
    }

}
