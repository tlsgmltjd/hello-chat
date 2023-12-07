package com.example.hellochat.domain.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RoomCreateRequest {
    private List<Long> participates;
}
