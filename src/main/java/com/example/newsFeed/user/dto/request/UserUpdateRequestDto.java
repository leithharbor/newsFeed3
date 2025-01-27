package com.example.newsFeed.user.dto.request;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String name;
    private String email;
    private String password;

    public UserUpdateRequestDto(){

    }
}
