package com._blog.demo.model.Entity;

import jakarta.persistence.*;

import lombok.*;

@Table
@Setter
@Getter
@NoArgsConstructor
@Entity
public class CommentReaction{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String action;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}
