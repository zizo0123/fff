package com._blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com._blog.demo.model.Entity.Post;
import com._blog.demo.service.Postservice;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/post")
public class PostController {
    @Autowired
    private Postservice postservice;

    @PostMapping("/addpost")
    public Post addPost(@RequestBody Post post){
        return postservice.savePost(post);
    }

    @GetMapping("/allposts")
    public List<Post> getAllPosts(){
        return postservice.getAllPosts();
    }
}
