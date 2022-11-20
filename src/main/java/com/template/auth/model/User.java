package com.template.auth.model;


import com.sun.istack.NotNull;
import lombok.*;
import org.aspectj.bridge.IMessage;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "auth_group_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns =  @JoinColumn(name = "group_id"))
    private Set<Group> groups = new HashSet<>();

}
