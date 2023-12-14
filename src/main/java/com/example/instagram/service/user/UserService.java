package com.example.instagram.service.user;

import com.example.instagram.common.exception.GlobalException;
import com.example.instagram.domain.user.User;
import com.example.instagram.repository.user.UserRepository;
import com.example.instagram.service.user.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.instagram.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long createUser(UserInfo info) {
        if (isDuplicatedByUsername(info.getUsername())) {
            throw new GlobalException(DUPLICATED_USERNAME);
        }

        if (isDuplicateByNickname(info.getNickname())) {
            throw new GlobalException(DUPLICATED_NICKNAME);
        }

        info.setPassword(encodePassword(info.getPassword()));
        User newUser = User.createUser(info);
        userRepository.save(newUser);

        return newUser.getId();
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Transactional(readOnly = true)
    public boolean isDuplicatedByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public boolean isDuplicateByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void login() {

    }

    @Transactional
    public void updatePassword(Long userId, String password) {
        User target = userRepository.findById(userId).orElseThrow(() -> {
            throw new GlobalException(USER_NOT_FOUND);
        });

        if (passwordEncoder.matches(password, target.getPassword())) {
            throw new GlobalException(DUPLICATED_PASSWORD);
        }

        String newPassword = encodePassword(password);
        target.changePassword(newPassword);
    }

    @Transactional
    public void updateNickname(Long userId, String nickname) {
        User target = userRepository.findById(userId).orElseThrow(() -> {
            throw new GlobalException(USER_NOT_FOUND);
        });

        if (userRepository.existsByNickname(nickname)) {
            throw new GlobalException(DUPLICATED_NICKNAME);
        }

        target.changeNickname(nickname);
    }

//    @Transactional
//    public LoginUserResponse login(LoginRequest request) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        String accessToken = jwtUtils.generateAccessTokenFromLoginUser(loginUser);
//        RefreshToken refreshToken = RefreshToken.builder()
//                .loginUser(loginUser)
//                .refreshToken(UUID.randomUUID().toString())
//                .build();
//        refreshTokenRepository.save(refreshToken);
//        return LoginUserResponse.builder()
//                .loginUser(loginUser)
//                .accessToken(accessToken)
//                .refreshToken(refreshToken.getRefreshToken())
//                .build();
//    }
}
