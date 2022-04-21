package com.springbootcrud.pp3_1_1.dao;

import com.springbootcrud.pp3_1_1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
