package com._blog.demo.model.Entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Table(name="posts")
@Entity
@Data
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer postid;
    private String description;
    private Double timestamp;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private User reactions;

    public List<User> getReactions() {
        return reactions;
    }
    public void addReaction(User user){
        reactions.add(user);
    }
    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
