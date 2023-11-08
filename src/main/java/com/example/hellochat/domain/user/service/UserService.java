package com.example.hellochat.domain.user.service;

import com.example.hellochat.domain.user.dto.request.LoginRequest;
import com.example.hellochat.domain.user.dto.request.SignupRequest;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.domain.user.repository.UserRepository;
import com.example.hellochat.global.exception.CustomException;
import com.example.hellochat.global.util.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.stream.Collectors;

import static com.example.hellochat.global.exception.ErrorCode.DUPLICATED_USERNAME;
import static com.example.hellochat.global.exception.ErrorCode.NOT_MATCH_INFORMATION;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.admin-token}")
    private String adminToken;

    public void signup(SignupRequest signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        boolean isUserDuplicated = userRepository.existsByName(username);
        if (isUserDuplicated) {
            throw new CustomException(DUPLICATED_USERNAME);
        }

        // 사용자 ROLE 확인 (admin = true 일 경우 아래 코드 수행)
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!(adminToken.equals(signupRequestDto.getAdminToken()))) {
                throw new CustomException(NOT_MATCH_INFORMATION);
            }

            role = UserRoleEnum.ADMIN;
        }

        UserEntity user = UserEntity.builder()
                .name(username)
                .password(password)
                .role(role)
                .explain(signupRequestDto.getExplain())
                .birth(signupRequestDto.getBirth())
                .build();

        userRepository.save(user);
    }

    // 로그인
    public UserEntity login(LoginRequest loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        UserEntity user = userRepository.findByName(username).orElseThrow(
                () -> new CustomException(NOT_MATCH_INFORMATION)
        );

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(NOT_MATCH_INFORMATION);
        }

        return user;
    }
}
