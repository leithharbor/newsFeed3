package com.example.newsFeed.user.dto.request;

import lombok.Getter;

@Getter
public class UserCreateRequestDto {
    private String name;
    private String email;
    private String password;

    public UserCreateRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
