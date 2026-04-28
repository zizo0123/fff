package com._blog.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._blog.demo.dto.Postdto;
import com._blog.demo.model.Entity.Post;
import com._blog.demo.model.Entity.User;
import com._blog.demo.repository.PostRepo;
import com._blog.demo.repository.UserRepo;
@Service
public class Postservice{
    @Autowired
    private PostRepo postrep;
    @Autowired
    private UserRepo userrepo;

    List<Post> getAllPosts;
    //save post
    public Post savePost(Postdto postdto){
        Post post=new Post();
         Integer useridP=postdto.getUserId();
        User user=userrepo.findById(useridP).orElseThrow(()->new RuntimeException("not found"));
        post.setUser(user);
        return postrep.save(post);
    }
    public List<Post> getAllPosts(){
        return postrep.findAll();
    }
    public Post likedPost(Integer postId, String userToken){
        Post post=postrep.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        User user=userrepo.findByToken(userToken).orElseThrow(()->new RuntimeException("user not found"));
        post.addReaction(user);
        return postrep.save(post);
    }
}