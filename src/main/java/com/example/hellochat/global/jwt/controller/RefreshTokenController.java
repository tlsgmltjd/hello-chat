package com.example.hellochat.global.jwt.controller;

import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.domain.user.repository.UserRepository;
import com.example.hellochat.global.exception.CustomException;
import com.example.hellochat.global.jwt.JwtUtil;
import com.example.hellochat.global.jwt.service.RefreshTokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.hellochat.global.exception.ErrorCode.INVALID_TOKEN;

@RestController
@RequestMapping("/refresh")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public static final String BEARER_PREFIX = "Bearer ";

    @GetMapping
    public ResponseEntity<Void> refresh(@RequestHeader("Refresh-Token") String token, HttpServletResponse response) {
        String refreshToken = token.substring(7);

        // 리프레쉬 토큰 검증
        refreshTokenService.refresh(refreshToken);
        // 유저 정보 빼오기
        Claims userinfo = jwtUtil.getUserInfoFromToken(refreshToken);
        String username = userinfo.getSubject();
        UserEntity user =  userRepository.findByName(username)
                .orElseThrow(() -> new CustomException(INVALID_TOKEN));

        // 에세스 토큰 재발급
        String accessToken = JwtUtil.createToken(user.getName(), user.getRole());
        response.addHeader(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + accessToken);

        return null;
    }
}
