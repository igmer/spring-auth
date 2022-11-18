package com.template.auth.controllers;


import com.template.auth.exceptions.RequestException;
import com.template.auth.model.Role;
import com.template.auth.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("/create")
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        Optional<Role> prevRole = roleService.findByname(role.getName().toUpperCase());
        if (prevRole.isPresent()) {
            throw new RequestException(HttpStatus.CONFLICT, "p-409", "Role already exists");
        }
        Role newRole = roleService.save(role);
        return new ResponseEntity<>(newRole, HttpStatus.OK);
    }
}
