package com.template.auth.controllers;


import com.template.auth.dto.GroupRequestDto;
import com.template.auth.exceptions.RequestException;
import com.template.auth.model.Group;
import com.template.auth.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {
    GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @PostMapping("/create")
    public ResponseEntity<Group> create(@Valid @RequestBody GroupRequestDto group) {
        Optional<Group> prevGroup = groupService.findByname(group.getName().toUpperCase());
        if (prevGroup.isPresent()) {
            throw new RequestException(HttpStatus.CONFLICT, "p-409", "Group already exists");
        }
        Group newGroup = groupService.addGroupWithRole(group);
        return new ResponseEntity<>(newGroup, HttpStatus.OK);
    }
}
