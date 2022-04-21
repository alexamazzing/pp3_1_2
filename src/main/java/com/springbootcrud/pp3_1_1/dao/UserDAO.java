package com.springbootcrud.pp3_1_1.dao;

import com.springbootcrud.pp3_1_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDAO extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
