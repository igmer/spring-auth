package com.template.auth.dto;

import com.template.auth.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class GroupRequestDto {
    private int id;
    private String name;
    private Set<Role> roles;
}
