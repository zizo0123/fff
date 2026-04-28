package com._blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._blog.demo.model.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{
    
}
