package com.example.newsFeed.feed.dto.request;

import lombok.Getter;

@Getter
public class FeedCreateUpdateRequestDto {
    private String title;
    private String content;

    public FeedCreateUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
