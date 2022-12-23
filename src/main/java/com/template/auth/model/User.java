package com.template.auth.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "auth_user", schema = "public")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String name;
    @Column(unique = true)
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "auth_group_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns =  @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();
    @Transient
    private List<String> _roles;

    public void setRoles(List<Role> roles){
        _roles = roles.stream().map(Role::getName).collect(Collectors.toList());
    }
    public List<String> setRoles(){
        return this._roles;
    }

}
