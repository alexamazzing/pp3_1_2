package com.springbootcrud.pp3_1_1.service;

import com.springbootcrud.pp3_1_1.model.User;

import java.util.List;

public interface UserService {

    User findById(long id);
    List<User> findAll();
    User saveUser(User user);
    void deleteById(long id);
}
