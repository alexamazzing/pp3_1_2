package com.springbootcrud.pp3_1_1.dao;

import com.springbootcrud.pp3_1_1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
