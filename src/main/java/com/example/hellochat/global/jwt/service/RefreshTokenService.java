package com.example.hellochat.global.jwt.service;

import com.example.hellochat.global.exception.CustomException;
import com.example.hellochat.global.jwt.JwtUtil;
import com.example.hellochat.global.jwt.entity.RefreshToken;
import com.example.hellochat.global.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

import static com.example.hellochat.global.exception.ErrorCode.INVALID_TOKEN;
import static com.example.hellochat.global.exception.ErrorCode.NOT_MATCH_INFORMATION;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void saveRefreshToken(String token, Long userId) {

        if (refreshTokenRepository.existsByUserId(userId))
                refreshTokenRepository.deleteByUserId(userId);

        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(token)
                .userId(userId)
                .build();

        refreshTokenRepository.save(refreshToken);
    }

    public void refresh(String refreshToken) {
        RefreshToken savedToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new CustomException(NOT_MATCH_INFORMATION));

        if (!jwtUtil.validateToken(savedToken.getRefreshToken()) || !savedToken.getRefreshToken().equals(refreshToken)) {
            refreshTokenRepository.deleteByRefreshToken(refreshToken);
            throw new CustomException(INVALID_TOKEN);
        }
    }
}
