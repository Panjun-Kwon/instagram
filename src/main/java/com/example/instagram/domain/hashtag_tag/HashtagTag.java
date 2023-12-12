package com.example.instagram.domain.hashtag_tag;

import com.example.instagram.domain.hashtag.Hashtag;
import com.example.instagram.domain.post.Post;
import jakarta.persistence.*;

@Entity
public class HashtagTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_tag_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
