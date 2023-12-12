package com.example.instagram.domain.post;

import jakarta.persistence.*;

@Entity
public class PostImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String url;
}
