package com._blog.demo.model.Entity;

import jakarta.persistence.*;

import lombok.*;

@Table(name="posts")
@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer postid;
    private String description;
    private Double timestamp;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private User users;

}
