package com.example.newsFeed.friend.controller;

import com.example.newsFeed.friend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    // 친구 요청 보내기


    // 친구 요청 대기 상태

    // 절교


}
