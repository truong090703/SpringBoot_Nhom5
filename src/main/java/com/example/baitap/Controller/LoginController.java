package com.example.baitap.Controller;

import com.example.baitap.Repository.RoleRepository;
import com.example.baitap.Repository.UserRepository;
import org.springframework.ui.Model;
import com.example.baitap.Entity.Role;
import com.example.baitap.Entity.UserDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserDemo());
        return "register";
    }

    @PostMapping("/process-register")
    public String processRegister(UserDemo user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            userRole = new Role("ADMIN");
            roleRepository.save(userRole);
        }

        user.getRoles().add(userRole);
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/users")
    public String viewUsersList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }
}