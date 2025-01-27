package com.example.newsFeed.friend.dto.request;

import com.example.newsFeed.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FriendCreateRequestDto {
    private User userId;
    private LocalDateTime createdAt;

}
