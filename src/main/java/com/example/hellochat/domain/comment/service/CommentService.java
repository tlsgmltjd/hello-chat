package com.example.hellochat.domain.comment.service;

import com.example.hellochat.domain.board.entity.Board;
import com.example.hellochat.domain.comment.dto.request.CreateCommentRequest;
import com.example.hellochat.domain.comment.entity.Comment;
import com.example.hellochat.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void createComment(CreateCommentRequest request, Board board) {
        commentRepository.save(new Comment(request.getContent(), board));
    }
}
