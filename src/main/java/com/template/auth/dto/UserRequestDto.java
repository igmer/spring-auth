package com.template.auth.dto;

import com.template.auth.model.Group;
import com.template.auth.model.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserRequestDto {
    @NotBlank(message = "field required")
    private String name;
    @NotBlank(message = "field required")
    private String username;
    @NotBlank(message = "field required")
    @Email(message = "email required")
    private String email;
    @NotBlank(message = "field required")
    @Size(min = 8, message = "password must contain a minimum of 8 characters")
    @Size(max = 20, message = "password must contain a max of 20 characters")
    private String password;
    private Set<Group> groups = new HashSet<>();

    public User toUser(){
        return User.builder().name(name).username(username).email(email).password(password).groups(groups).build();
    }
}
