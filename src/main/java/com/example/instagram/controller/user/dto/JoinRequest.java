package com.example.instagram.controller.user.dto;

import com.example.instagram.service.user.dto.UserInfo;
import lombok.Getter;

@Getter
public class JoinRequest {
    private String username;
    private String password;
    private String nickname;
    private String name;

    public UserInfo toUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setNickname(nickname);
        userInfo.setName(name);

        return userInfo;
    }
}
