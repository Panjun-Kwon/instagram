package com.example.instagram.domain.user_post_tag;

import com.example.instagram.domain.post.Post;
import com.example.instagram.domain.user.User;
import jakarta.persistence.*;

@Entity
public class UserPostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_post_tag_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
