package com.example.baitap.Controller;

import com.example.baitap.Entity.UserDemo;
import com.example.baitap.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<UserDemo> users() {
        return userService.getAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserDemo> getUserById(@PathVariable int id) {
        UserDemo user = userService.findById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
    // Create a new user (POST /api/users)
    @PostMapping("/api/users")
    public UserDemo createUser(@RequestBody UserDemo user) {
        return userService.saveOrUpdate(user);
    }
    // Update a user (PUT /api/users/{id})
    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserDemo> updateUser(@PathVariable int id, @RequestBody UserDemo updatedUser) {
        UserDemo existingUser = userService.findById(id);
        if (existingUser != null) {
            updatedUser.setId(id);
            userService.saveOrUpdate(updatedUser);
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a user (DELETE /api/users/{id})
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        UserDemo user = userService.findById(id);
        if (user != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


