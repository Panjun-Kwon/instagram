package com.example.instagram.repository.user;

import com.example.instagram.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByNickname(String nickname);

    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
}
