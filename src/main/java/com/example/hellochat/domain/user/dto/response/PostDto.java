package com.example.hellochat.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PostDto {
    private final Long id;
    private final String title;
    private final String content;
    private final Integer likeCount;
    private final Integer commentCount;
}
