package com._blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._blog.demo.model.Entity.Reaction;
import com._blog.demo.model.Entity.Post;
import com._blog.demo.model.Entity.User;
import com._blog.demo.repository.ReactionRepo;
import com._blog.demo.repository.PostRepo;
import com._blog.demo.repository.UserRepo;
@Service
public class Reactionservice {
    @Autowired 
    private ReactionRepo Reactionrepo;
    @Autowired
    private PostRepo postrep;
    @Autowired
    private UserRepo userrepo;
    //save post
    public Reaction saveReaction(Reaction Reaction){
         Integer useridd=Reaction.getUser().getUserid();
        User user=userrepo.findById(useridd).orElseThrow(()->new RuntimeException("not found"));
        Reaction.setUser(user);
     Integer postid=Reaction.getPost().getPostid();
        Post post=postrep.findById(postid).orElseThrow(()->new RuntimeException("not found"));
        Reaction.setPost(post);
        return Reactionrepo.save(Reaction);
    }
}
