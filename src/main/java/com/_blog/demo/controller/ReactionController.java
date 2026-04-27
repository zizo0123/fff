package com._blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._blog.demo.model.Entity.Reaction;
import com._blog.demo.service.Reactionservice;
@RestController

@RequestMapping("/Reaction")
public class ReactionController {
    @Autowired
    private Reactionservice Reactionservice;

    @PostMapping("/addReaction")
    public Reaction addReaction(@RequestBody Reaction Reaction){
        return Reactionservice.saveReaction(Reaction);
    }
}
