package com.example.hellochat.domain.board.service;

import com.example.hellochat.domain.board.dto.request.CreateBoardRequest;
import com.example.hellochat.domain.board.dto.response.BoardsAuthorDto;
import com.example.hellochat.domain.board.dto.response.BoardsResponse;
import com.example.hellochat.domain.board.entity.Board;
import com.example.hellochat.domain.board.repository.BoardRepository;
import com.example.hellochat.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void postBoard(CreateBoardRequest request, UserEntity user) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .authorId(user)
                .likes(List.of())
                .build();

        boardRepository.save(board);
    }

    // 추천 시스템, 페이징 시스템 추가 예정
    @Transactional(readOnly = true)
    public List<BoardsResponse> findBoad() {
        return boardRepository.findAll().stream()
                .map(board -> BoardsResponse.builder()
                        .boardId(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .author(new BoardsAuthorDto(board.getAuthorId().getUsersId(), board.getAuthorId().getName()))
                        .likeCount(board.getLikes().size())
                        .commentCount(board.getComment().size()).build())
                .toList();
    }
}
