package com.example.newsFeed.user.dto.response;

import com.example.newsFeed.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime updatedAt;
    private String updatedMessage;

    public UserUpdateResponseDto(User updatedUser, String updatedMessage) {
        this.name = updatedUser.getName();
        this.email = updatedUser.getEmail();
        this.id = updatedUser.getId();;
        this.updatedAt = updatedUser.getUpdatedAt();
        this.updatedMessage = updatedMessage;
    }
}
