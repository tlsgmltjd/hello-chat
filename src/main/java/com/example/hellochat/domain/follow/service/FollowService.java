package com.example.hellochat.domain.follow.service;

import com.example.hellochat.domain.follow.entity.Follow;
import com.example.hellochat.domain.follow.repository.FollowRepository;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.domain.user.repository.UserRepository;
import com.example.hellochat.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.hellochat.global.exception.ErrorCode.NOT_MATCH_INFORMATION;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void follow(Long userId, UserEntity toUser) {
        UserEntity fromUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(NOT_MATCH_INFORMATION));

        followRepository.save(Follow.builder()
                .toUser(toUser)
                .fromUser(fromUser)
                .build());
    }
}
