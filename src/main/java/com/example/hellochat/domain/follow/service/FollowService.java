package com.example.hellochat.domain.follow.service;

import com.example.hellochat.domain.follow.dto.response.FollowResponse;
import com.example.hellochat.domain.follow.dto.response.FollowUserInfo;
import com.example.hellochat.domain.follow.entity.Follow;
import com.example.hellochat.domain.follow.repository.FollowRepository;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.domain.user.repository.UserRepository;
import com.example.hellochat.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.example.hellochat.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public void follow(Long userId, UserEntity toUser) {
        UserEntity fromUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(NOT_MATCH_INFORMATION));

        if (fromUser.getUsersId().equals(toUser.getUsersId()) || followRepository.existsByFromUser(fromUser))
            throw new CustomException(DONT_FOLLOW);

        followRepository.save(Follow.builder()
                .toUser(toUser)
                .fromUser(fromUser)
                .build());
    }

    @Transactional
    public void unfollow(Long userId, UserEntity toUser) {
        UserEntity fromUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(NOT_MATCH_INFORMATION));

        Follow follow = followRepository.findByFromUser(fromUser)
                .orElseThrow(() -> new CustomException(NOT_FOLLOW_INFORMATION));

        if (!Objects.equals(follow.getToUser().getUsersId(), toUser.getUsersId()))
            throw new CustomException(DONT_UNFOLLOW);

        followRepository.delete(follow);
    }

    @Transactional(readOnly = true)
    public List<FollowResponse> findFollower(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(NOT_MATCH_INFORMATION));

        return user.getFollowers().stream()
                .map(follower ->
                        FollowResponse.builder()
                                .id(follower.getId())
                                .user(FollowUserInfo.builder()
                                        .id(follower.getFromUser().getUsersId())
                                        .username(follower.getFromUser().getName())
                                        .build())
                                .build())
                .toList();
    }

    @Transactional(readOnly = true)
    public List<FollowResponse> findFollowing(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(NOT_MATCH_INFORMATION));

        return user.getFollowers().stream()
                .map(follower ->
                        FollowResponse.builder()
                                .id(follower.getId())
                                .user(FollowUserInfo.builder()
                                        .id(follower.getToUser().getUsersId())
                                        .username(follower.getToUser().getName())
                                        .build())
                                .build())
                .toList();
    }
}
