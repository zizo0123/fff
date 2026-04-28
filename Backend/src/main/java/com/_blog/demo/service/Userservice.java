// ...existing code...
package com._blog.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._blog.demo.model.Entity.User;
import com._blog.demo.repository.UserRepo;

@Service
public class Userservice {
    @Autowired
    private UserRepo userrep;

    // added: save a user
    public User saveUser(User user){
        return userrep.save(user);
    }
}