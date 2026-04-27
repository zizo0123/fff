package com._blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com._blog.demo.model.Entity.Post;

public interface PostRepo extends JpaRepository<Post,Integer>{

    
}
