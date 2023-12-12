package com.example.instagram.domain.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String password;

    private String profileImage;
    @Column(nullable = false, unique = true)
    private String nickname;
    private String description;

    @Column(nullable = false, updatable = false)
    private String name;
    private LocalDate birth;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String email;
    private String phoneNumber;
    @Embedded
    private Address address;
}
