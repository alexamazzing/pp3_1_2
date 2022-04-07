package com.springbootcrud.pp3_1_1.controller;


import com.springbootcrud.pp3_1_1.model.User;
import com.springbootcrud.pp3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String printUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("/{id}/update")
    public String editUser(@PathVariable("id") int id, @ModelAttribute User user, Model model) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteUserById(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
