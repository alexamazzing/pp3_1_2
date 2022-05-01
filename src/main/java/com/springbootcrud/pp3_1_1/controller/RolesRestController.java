package com.springbootcrud.pp3_1_1.controller;

import com.springbootcrud.pp3_1_1.model.Role;
import com.springbootcrud.pp3_1_1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RolesRestController {

    private final RoleService roleService;

    @Autowired
    public RolesRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/api/roles")
    public Set<Role> roleSet() {
        return roleService.getAllRoles();
    }

}
