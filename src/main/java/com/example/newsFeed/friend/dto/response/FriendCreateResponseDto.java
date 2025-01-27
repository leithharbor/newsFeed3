package com.example.newsFeed.friend.dto.response;

import com.example.newsFeed.entity.User;
import lombok.Getter;

@Getter
public class FriendCreateResponseDto {

    private Long id; //
    private User follower;
    private User following;
}
