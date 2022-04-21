package com.springbootcrud.pp3_1_1.controller;


import com.springbootcrud.pp3_1_1.model.User;
import com.springbootcrud.pp3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping()
//    public String indexPage() {
//        return "index";
//    }

    @GetMapping("/all")
   // @PreAuthorize("hasRole('ADMIN')")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String userPage(Model model) {
        return "user";
    }

    @GetMapping("/admin/new")
    @PreAuthorize("hasRole('ADMIN')")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/admin/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PostMapping("admin/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String editUser(@PathVariable("id") int id, @ModelAttribute User user, Model model) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
