package com.example.hellochat.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
public record PostDto (
        Long id,
        String title,
        String content,
        Integer likeCount,
        Integer commentCount
) {}
