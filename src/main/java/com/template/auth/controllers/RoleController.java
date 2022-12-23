package com.template.auth.controllers;


import com.template.auth.model.Role;
import com.template.auth.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping("/create")
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        Role newRole = roleService.save(role);
        return new ResponseEntity<>(newRole, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
