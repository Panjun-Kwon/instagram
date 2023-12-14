package com.example.instagram.controller.user;

import com.example.instagram.common.exception.Response;
import com.example.instagram.controller.user.dto.JoinRequest;
import com.example.instagram.service.user.UserService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/check/username")
    public Response<Void> validateDuplicateUsername(@RequestParam("value") String username) {
        if (userService.isDuplicatedByUsername(username)) {
            return Response.success("사용 가능한 아이디", null);
        } else {
            return Response.success("사용 불가능한 아이디", null);
        }
    }

    @GetMapping("/check/nickname")
    public Response<Void> validateDuplicateNickname(@RequestParam("value") String nickname) {
        if (userService.isDuplicateByNickname(nickname)) {
            return Response.success("사용 가능한 닉네임", null);
        } else {
            return Response.success("사용 불가능한 닉네임", null);
        }
    }

    @PatchMapping("/me/password")
    public Response<Void> changePassword(@RequestBody String password) {
        Long userId = 1L; // 임시
        userService.updatePassword(userId, password);

        return Response.success("비밀번호 변경 성공", null);
    }

    @PatchMapping("/me/nickname")
    public Response<Void> changeNickname(@RequestBody String nickname) {
        Long userId = 1L; // 임시
        userService.updateNickname(userId, nickname);

        return Response.success("닉네임 변경 성공", null);
    }

    @PostMapping("/join")
    public Response<Void> join(@RequestBody JoinRequest request) {
        userService.createUser(request.toUserInfo());

        return Response.success("회원가입 성공", null);
    }

    @PostMapping("/login")
    public Response<Void> login() {
        return Response.success("로그인 성공.", null);
    }
}
