package com.template.auth.controllers;


import com.template.auth.dto.GroupRequestDto;
import com.template.auth.model.Group;
import com.template.auth.services.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {
    GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @PostMapping("/create")
    public ResponseEntity<Group> create(@Valid @RequestBody GroupRequestDto group) {
        Group newGroup = groupService.addGroupWithRole(group);
        return new ResponseEntity<>(newGroup, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<Group>> getGroups() {
        List<Group> groups = groupService.getAll();
        return new ResponseEntity<>(groups, HttpStatus.OK);
    }
}
