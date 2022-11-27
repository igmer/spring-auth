package com.template.auth.services;

import com.template.auth.exceptions.RequestException;
import com.template.auth.model.Role;
import com.template.auth.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Role> prevRole = roleRepository.findByName(role.getName().toUpperCase());
        if (prevRole.isPresent()) {
            throw new RequestException(HttpStatus.CONFLICT, "p-409", "Role already exists");
        }
        role.setName(role.getName().toUpperCase());
        return roleRepository.save(role);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
