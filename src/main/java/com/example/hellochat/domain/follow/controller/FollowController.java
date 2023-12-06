package com.example.hellochat.domain.follow.controller;

import com.example.hellochat.domain.follow.dto.request.FollowRequest;
import com.example.hellochat.domain.follow.service.FollowService;
import com.example.hellochat.global.security.UserDetailsImpl;
import com.example.hellochat.global.util.MsgResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping
    public ResponseEntity<MsgResponseDto> follow(@RequestBody FollowRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        followService.follow(request.getUserId(), userDetails.getUser());
        return ResponseEntity.ok(new MsgResponseDto("팔로우 등록이 완료되었습니다.", HttpStatus.NO_CONTENT.value()));
    }
}
