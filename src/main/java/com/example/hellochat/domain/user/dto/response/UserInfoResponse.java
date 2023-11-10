package com.example.hellochat.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoResponse {
    private Long id;
    private String username;
    private String explain;
    private Long followers;
    private Long following;
    private Boolean isFollowed;
    private List<PostDto> posts;
}
