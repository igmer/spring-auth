package com.template.auth.repository;

import com.template.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String role);

    Role findById(int id);

    @Query(value = "SELECT *  FROM auth_role ar " +
            "INNER JOIN auth_role_group arg ON arg.role_id =ar.id " +
            "INNER JOIN auth_group_user agu ON agu.group_id = arg.group_id " +
            "WHERE agu.user_id = :userId", nativeQuery = true)
    List<Role> findAllByUser(int userId);
}
