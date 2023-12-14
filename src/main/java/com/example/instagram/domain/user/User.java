package com.example.instagram.domain.user;

import com.example.instagram.service.user.dto.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static com.example.instagram.domain.user.Role.USER;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;
    @Column(nullable = false, unique = true)
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;

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

    public static User createUser(UserInfo info) {
        User user = new User();
        user.setInfo(info);

        return user;
    }

    private void setInfo(UserInfo info) {
        username = info.getUsername();
        password = info.getPassword();

        profileImage = info.getProfileImage();
        nickname = info.getNickname();
        description = info.getDescription();

        name = info.getName();
        birth = info.getBirth();
        gender = info.getGender();
        email = info.getEmail();
        phoneNumber = info.getPhoneNumber();
        address = info.getAddress();

        role = USER;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
