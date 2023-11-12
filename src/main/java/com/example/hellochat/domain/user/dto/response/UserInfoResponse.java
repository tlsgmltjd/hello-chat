package com.example.hellochat.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
public record UserInfoResponse (
    Long id,
    String username,
    String explain,
    Long followers,
    Long following,
    Boolean isFollowed,
    List<PostDto> posts
) {}