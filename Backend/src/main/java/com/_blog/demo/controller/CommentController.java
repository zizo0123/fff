package com._blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._blog.demo.model.Entity.Comment;
import com._blog.demo.service.*;
@RestController

@RequestMapping("/Comment")
public class CommentController {
    @Autowired
    private Commentservice Commentservice;

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment Comment){
        return Commentservice.saveComment(Comment);
    }
}
