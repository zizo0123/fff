package com._blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._blog.demo.model.Entity.Comment;
import com._blog.demo.model.Entity.Post;
import com._blog.demo.model.Entity.User;
import com._blog.demo.repository.CommentRepo;
import com._blog.demo.repository.PostRepo;
import com._blog.demo.repository.UserRepo;

@Service
public class Commentservice {
    @Autowired 
    private CommentRepo commentrepo;
    @Autowired
    private PostRepo postrep;
    @Autowired
    private UserRepo userrepo;
    //save post
    public Comment saveComment(Comment comment){
         Integer useridd=comment.getUser().getUserid();
        User user=userrepo.findById(useridd).orElseThrow(()->new RuntimeException("not found"));
        comment.setUser(user);
     Integer postid=comment.getPost().getPostid();
        Post post=postrep.findById(postid).orElseThrow(()->new RuntimeException("not found"));
        comment.setPost(post);
        return commentrepo.save(comment);
    }
}
