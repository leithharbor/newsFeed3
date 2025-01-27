package com.example.newsFeed.friend.service;

import com.example.newsFeed.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendRepository friendRepository;
}
