package com.template.auth.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "auth_role", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;
    @NotNull
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<Group> groups = new HashSet<>();

    public void addGroup(Group group){
        this.getGroups().add(group);
    }

}
