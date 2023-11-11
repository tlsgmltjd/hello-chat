package com.example.hellochat.domain.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
