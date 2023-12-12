package com.example.instagram.domain.user_comment_tag;

import com.example.instagram.domain.comment.Comment;
import com.example.instagram.domain.user.User;
import jakarta.persistence.*;

@Entity
public class UserCommentTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_comment_tag_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
