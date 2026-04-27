package com._blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
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
    //save post
    public Post savePost(Post post){
         Integer useridd=post.getUsers().getUserid();
        User users=userrepo.findById(useridd).orElseThrow(()->new RuntimeException("not found"));
        post.setUsers(users);
        return postrep.save(post);
    }
    public List<Post> getAllPosts(){
    return postrep.findAll();
}
}