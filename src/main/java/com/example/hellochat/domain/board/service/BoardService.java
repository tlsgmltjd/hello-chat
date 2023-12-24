package com.example.hellochat.domain.board.service;

import com.example.hellochat.domain.board.dto.request.BoardLikeRequest;
import com.example.hellochat.domain.board.dto.request.CreateBoardRequest;
import com.example.hellochat.domain.board.dto.response.BoardAuthorDto;
import com.example.hellochat.domain.board.dto.response.BoardCommentsDto;
import com.example.hellochat.domain.board.dto.response.BoardResponse;
import com.example.hellochat.domain.board.dto.response.BoardsResponse;
import com.example.hellochat.domain.board.entity.Board;
import com.example.hellochat.domain.board.repository.BoardRepository;
import com.example.hellochat.domain.comment.entity.Comment;
import com.example.hellochat.domain.comment.repository.CommentRepository;
import com.example.hellochat.domain.user.entity.UserEntity;
import com.example.hellochat.global.exception.CustomException;
import static com.example.hellochat.global.exception.ErrorCode.*;

import com.example.hellochat.global.util.MsgResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

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
    public List<BoardsResponse> findBoards() {
        return boardRepository.findAll().stream()
                .map(board -> BoardsResponse.builder()
                        .boardId(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .author(new BoardAuthorDto(board.getAuthorId().getUsersId(), board.getAuthorId().getName()))
                        .likeCount(board.getLikes().size())
                        .commentCount(board.getComment().size()).build())
                .toList();
    }

    @Transactional(readOnly = true)
    public BoardResponse findBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomException(NOT_FOUND));

        List<Comment> comments = commentRepository.findByBoardId(board);

        return BoardResponse.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(new BoardAuthorDto(board.getAuthorId().getUsersId(), board.getAuthorId().getName()))
                .comments(comments.stream()
                        .map(comment -> BoardCommentsDto.builder()
                                .id(comment.getId())
                                .content(comment.getContent())
                                .date(comment.getDate())
                                .author(new BoardAuthorDto(comment.getAuthorId().getUsersId(), comment.getAuthorId().getName()))
                                .build())
                        .toList())
                .build();
    }

    @Transactional
    public MsgResponseDto likeBoard(BoardLikeRequest request, UserEntity user) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new CustomException(NOT_FOUND));

        if (board.getLikes().stream()
                .noneMatch(likedId -> Objects.equals(likedId, user.getUsersId()))) {
            board.addLike(user.getUsersId());
            return new MsgResponseDto("좋아요 등록이 완료되었습니다.", HttpStatus.NO_CONTENT.value());
        } else {
            board.removeLike(user.getUsersId());
            return new MsgResponseDto("좋아요 취소가 완료되었습니다.", HttpStatus.NO_CONTENT.value());
        }
    }
}
