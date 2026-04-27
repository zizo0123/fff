package com._blog.demo.model.Entity;

import jakarta.persistence.*;

import lombok.*;

@Table
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String ccontent;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
}
