package com.template.auth.repository;

import com.template.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String role);
    Role findById(int id);
}
