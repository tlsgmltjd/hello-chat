package com.example.hellochat.domain.user.service;

import com.example.hellochat.domain.user.dto.response.PostDto;
import com.example.hellochat.domain.user.dto.response.UserInfoResponse;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.domain.user.repository.UserRepository;
import com.example.hellochat.global.exception.CustomException;
import static com.example.hellochat.global.exception.ErrorCode.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserRepository userRepository;

    public UserInfoResponse findUser(Long userId, UserEntity user) {
        // 추가 정보 불러오는 부분 추가. 팔로우, 팔로잉, 게시글

        UserEntity findUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(NOT_MATCH_INFORMATION));

        return UserInfoResponse.builder()
                .id(findUser.getUsersId())
                .username(findUser.getName())
                .explain(findUser.getExplain())
                .followers(0L) // temp
                .following(0L) // temp
                .isFollowed(Objects.equals(findUser.getUsersId(), user.getUsersId()) ? null : false) // temp
                .posts(findUser.getPosts().stream().map(
                        post -> new PostDto(post.getId(), post.getTitle(), post.getContent(), (long) post.getLikes().size(), 0L) // update comment
                ).toList())
                .build();
    }
}
