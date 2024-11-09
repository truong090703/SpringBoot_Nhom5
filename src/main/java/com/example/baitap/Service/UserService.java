package com.example.baitap.Service;


import com.example.baitap.Entity.UserDemo;
import com.example.baitap.Repository.RoleRepository;
import com.example.baitap.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public UserDemo saveOrUpdate(UserDemo user)
    {
       userRepository.save(user);
        return user;
    }


    public List<UserDemo> getAllUsers() {
        return (List<UserDemo>) userRepository.findAll();
    }

    public UserDemo findById(Integer id) {
        Optional<UserDemo> user = userRepository.findById(id);
        return user.orElse(null);
    }
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}