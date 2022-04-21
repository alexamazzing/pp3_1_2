package com.springbootcrud.pp3_1_1.service;

import com.springbootcrud.pp3_1_1.dao.RoleDAO;
import com.springbootcrud.pp3_1_1.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Set<Role> getAllRoles() {
        return new HashSet<>(roleDAO.findAll());
    }

    public Role getRole(String role) {
        return roleDAO.findByName(role);
    }
}