package com.springbootcrud.pp3_1_1.service;

import com.springbootcrud.pp3_1_1.dao.RoleDAO;
import com.springbootcrud.pp3_1_1.dao.UserDAO;
import com.springbootcrud.pp3_1_1.model.Role;
import com.springbootcrud.pp3_1_1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    public User findById(Long userId) {
        Optional<User> userFromDb = userDAO.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userDAO.findByEmail(user.getEmail()).orElse(null);

        if (userFromDB != null) {
            return false;
        }

        Set<Role> roles = user.getRoles();
        if (roles.size() == 0) {
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
        return true;
    }

    public boolean deleteById(Long userId) {
        if (userDAO.findById(userId).isPresent()) {
            userDAO.deleteById(userId);
            return true;
        }
        return false;
    }

}
