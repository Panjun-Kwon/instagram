package com.example.instagram.domain.hashtag_follow;

import com.example.instagram.domain.hashtag.Hashtag;
import com.example.instagram.domain.user.User;
import jakarta.persistence.*;

@Entity
public class HashtagFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_follow_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
