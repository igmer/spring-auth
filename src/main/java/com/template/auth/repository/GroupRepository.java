package com.template.auth.repository;

import com.template.auth.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GroupRepository extends JpaRepository<Group,Integer> {
    Optional<Group> findByName(String name);
}
