package com.example.hellochat.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    private final Long id;
    private final String username;
    private final String explain;
    private final Long followers;
    private final Long following;
    private final Boolean isFollowed;
    private final List<PostDto> posts;
}
