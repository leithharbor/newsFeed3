package com.example.newsFeed.user.dto.response;

import com.example.newsFeed.entity.User;
import lombok.Getter;


@Getter
public class UserFindAllResponseDto {
    private Long id;
    private String name;
    private String email;

    public UserFindAllResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
