package com._blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._blog.demo.model.Entity.CommentReaction;
import com._blog.demo.model.Entity.Post;
import com._blog.demo.model.Entity.User;
import com._blog.demo.repository.CommentRRepo;
import com._blog.demo.repository.PostRepo;
import com._blog.demo.repository.UserRepo;

@Service
public class CommentReactionservice {
    @Autowired 
    private CommentRRepo CommentRrepo;
    @Autowired
    private PostRepo postrep;
    @Autowired
    private UserRepo userrepo;
    //save post
    public CommentReaction saveCommentReaction(CommentReaction Commentreaction){
         Integer useridd=Commentreaction.getUser().getUserid();
        User user=userrepo.findById(useridd).orElseThrow(()->new RuntimeException("not found"));
        Commentreaction.setUser(user);
     Integer postid=Commentreaction.getPost().getPostid();
        Post post=postrep.findById(postid).orElseThrow(()->new RuntimeException("not found"));
        Commentreaction.setPost(post);
        return CommentRrepo.save(Commentreaction);
    }
}
