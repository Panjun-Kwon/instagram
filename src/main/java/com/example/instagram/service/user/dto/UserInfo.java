package com.example.instagram.service.user.dto;

import com.example.instagram.domain.user.Address;
import com.example.instagram.domain.user.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfo {
    private String username;
    private String password;

    private String profileImage;
    private String nickname;
    private String description;

    private String name;
    private LocalDate birth;
    private Gender gender;
    private String email;
    private String phoneNumber;
    private Address address;
}
