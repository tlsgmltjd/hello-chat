package com.example.hellochat.domain.user.controller;

import com.example.hellochat.domain.user.dto.response.SearchUserInfoResponse;
import com.example.hellochat.domain.user.dto.response.UserIdResponse;
import com.example.hellochat.domain.user.dto.response.UserInfoResponse;
import com.example.hellochat.domain.user.service.UserInfoService;
import com.example.hellochat.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoResponse> userFind(@PathVariable Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(userInfoService.findUser(userId, userDetails.getUser()));
    }

    @GetMapping
    public ResponseEntity<List<SearchUserInfoResponse>> userSearchFind(@RequestParam String search) {
        return ResponseEntity.ok(userInfoService.findUserSearch(search));
    }

    @GetMapping("/me")
    public ResponseEntity<UserIdResponse> userMeFind(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(new UserIdResponse(userDetails.getUser().getUsersId()));
    }
}
