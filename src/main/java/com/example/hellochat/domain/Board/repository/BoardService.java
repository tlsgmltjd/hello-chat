package com.example.hellochat.domain.Board.repository;

import com.example.hellochat.domain.Board.dto.request.CreateBoardRequest;
import com.example.hellochat.domain.Board.entity.Board;
import com.example.hellochat.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void postBoard(CreateBoardRequest request, UserEntity user) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .authorId(user)
                .likes(List.of())
                .build();

        boardRepository.save(board);
    }
}
