package com.example.hellochat.domain.user.controller;

import com.example.hellochat.domain.user.service.UserService;
import com.example.hellochat.domain.user.dto.request.LoginRequest;
import com.example.hellochat.domain.user.dto.request.SignupRequest;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.global.jwt.JwtUtil;
import com.example.hellochat.global.jwt.service.RefreshTokenService;
import com.example.hellochat.global.util.MsgResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    public static final String BEARER_PREFIX = "Bearer ";

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@RequestBody @Valid SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.CREATED.value()));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MsgResponseDto> login(@RequestBody @Valid LoginRequest loginRequestDto, HttpServletResponse response) {
        UserEntity user = userService.login(loginRequestDto);

        String refreshToken = JwtUtil.createRefreshToken(user.getName());
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, BEARER_PREFIX + JwtUtil.createToken(user.getName(), user.getRole()));
        response.addHeader("Refresh-Token", BEARER_PREFIX + refreshToken);

        refreshTokenService.saveRefreshToken(refreshToken, user.getUsersId());

        return ResponseEntity.ok(new MsgResponseDto("로그인 완료", HttpStatus.OK.value()));
    }
}
