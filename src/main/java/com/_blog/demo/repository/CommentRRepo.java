package com._blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._blog.demo.model.Entity.CommentReaction;

public interface CommentRRepo extends JpaRepository<CommentReaction,Integer>{
    
}
