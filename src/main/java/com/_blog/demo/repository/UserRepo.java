package com._blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._blog.demo.model.Entity.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}