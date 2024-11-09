package com.example.baitap.Controller;

import com.example.baitap.Entity.Role;
import com.example.baitap.Entity.UserDemo;
import com.example.baitap.Repository.RoleRepository;
import com.example.baitap.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RestRegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // API để đăng ký người dùng mới
    @PostMapping("/register")
    public ResponseEntity<UserDemo> registerUser(@RequestBody UserDemo userDemo) {
        Set<Role> roles = new HashSet<>();
        for (Role role : userDemo.getRoles()) {
            Role existingRole = roleRepository.findByName(role.getName());
            if (existingRole == null) {
                existingRole = new Role(role.getName());
                roleRepository.save(existingRole);
            }
            roles.add(existingRole);
        }

        userDemo.setRoles(roles);
        userDemo.setPassword(passwordEncoder.encode(userDemo.getPassword()));

        UserDemo savedUser = userService.saveOrUpdate(userDemo);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // API để lấy thông tin tất cả người dùng đã đăng ký
    @GetMapping("/register")
    public ResponseEntity<List<UserDemo>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // API để cập nhật thông tin người dùng
    @PutMapping("/register/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserDemo userDetails) {
        UserDemo existingUser = userService.findById(id);
        if (existingUser != null) {
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setEmail(userDetails.getEmail());

            if (!existingUser.getPassword().equals(userDetails.getPassword())) {
                existingUser.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
            existingUser.setCompany(userDetails.getCompany());

            Set<Role> roles = new HashSet<>();
            for (Role role : userDetails.getRoles()) {
                Role existingRole = roleRepository.findByName(role.getName());
                if (existingRole == null) {
                    existingRole = new Role(role.getName());
                    roleRepository.save(existingRole);
                }
                roles.add(existingRole);
            }
            existingUser.setRoles(roles);

            UserDemo updatedUser = userService.saveOrUpdate(existingUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // API để xóa người dùng
    @DeleteMapping("/register/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User with ID " + id + " has been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
