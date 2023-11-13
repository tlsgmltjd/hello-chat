package com.example.hellochat.domain.comment.controller;

import com.example.hellochat.domain.comment.dto.request.CreateCommentRequest;
import com.example.hellochat.domain.comment.service.CommentService;
import com.example.hellochat.global.security.UserDetailsImpl;
import com.example.hellochat.global.util.MsgResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<MsgResponseDto> commentCreate(@PathVariable Long boardId, @RequestBody @Valid CreateCommentRequest request,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.createComment(request, boardId, userDetails.getUser());
        return ResponseEntity.ok(new MsgResponseDto("댓글이 정상적으로 저장되었습니다.", HttpStatus.OK.value()));
    }
}
