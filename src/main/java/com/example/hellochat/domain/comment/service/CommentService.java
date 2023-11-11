package com.example.hellochat.domain.comment.service;

import com.example.hellochat.domain.board.entity.Board;
import com.example.hellochat.domain.board.repository.BoardRepository;
import com.example.hellochat.domain.comment.dto.request.CreateCommentRequest;
import com.example.hellochat.domain.comment.entity.Comment;
import com.example.hellochat.domain.comment.repository.CommentRepository;
import com.example.hellochat.global.exception.CustomException;
import static com.example.hellochat.global.exception.ErrorCode.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public void createComment(CreateCommentRequest request, Long boardId) {
        commentRepository.save(new Comment(request.getContent(), boardRepository.findById(boardId).orElseThrow(() -> new CustomException(BAD_REQUEST))));
    }
}
