package com.template.auth.services;

import com.template.auth.dto.GroupRequestDto;
import com.template.auth.model.Group;
import com.template.auth.model.Role;
import com.template.auth.repository.GroupRepository;
import com.template.auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;
    public GroupService(GroupRepository groupRepository, RoleRepository roleRepository) {
        this.groupRepository = groupRepository;
        this.roleRepository = roleRepository;
    }
    public Optional<Group> findByname(String group){
        return groupRepository.findByName(group);
    }
    public Group save(Group group){
        return groupRepository.save(group);
    }

    public Group addGroupWithRole(GroupRequestDto groupRequest){
        Group group = new Group();
        group.setId(group.getId());
        group.setName(groupRequest.getName());
        group.setRoles(groupRequest.getRoles()
                .stream()
                .map(role -> {
                    Role rrole = role;
                    if (rrole.getId() > 0){
                        rrole = roleRepository.findById(rrole.getId());
                    }
                    rrole.addGroup(group);
                    return rrole;
                }).collect(Collectors.toSet()));

        return groupRepository.save(group);

    }

}
