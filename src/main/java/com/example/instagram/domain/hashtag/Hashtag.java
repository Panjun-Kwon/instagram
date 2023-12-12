package com.example.instagram.domain.hashtag;

import jakarta.persistence.*;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id", updatable = false)
    private Long id;

    private String tagName;
}
