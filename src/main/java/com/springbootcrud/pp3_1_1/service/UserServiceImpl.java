package com.springbootcrud.pp3_1_1.service;

import com.springbootcrud.pp3_1_1.dao.UserDAO;
import com.springbootcrud.pp3_1_1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User findById(long id) {
        return userDAO.getById(id);
    }

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User saveUser(User user) {
        return userDAO.save(user);
    }

    public void deleteById(long id) {
        userDAO.deleteById(id);
    }
}
