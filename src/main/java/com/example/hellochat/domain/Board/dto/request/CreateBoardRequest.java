package com.example.hellochat.domain.Board.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {
    private String title;
    private String content;
}
