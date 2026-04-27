package com._blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._blog.demo.model.Entity.CommentReaction;
import com._blog.demo.service.CommentReactionservice;
@RestController

@RequestMapping("/CommentReaction")
public class CommentRController {
    @Autowired
    private CommentReactionservice Commentrservice;

    @PostMapping("/addCommentReaction")
    public CommentReaction addCommentReaction(@RequestBody CommentReaction CommentReaction){
        return Commentrservice.saveCommentReaction(CommentReaction);
    }
}
