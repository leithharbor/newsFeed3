package com.example.newsFeed.feed.dto.response;

import com.example.newsFeed.entity.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedCreateResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public FeedCreateResponseDto (Feed savedFeed) {
        this.id = savedFeed.getId();
        this.title = savedFeed.getTitle();
        this.content = savedFeed.getContent();
        this.createdAt = savedFeed.getCreatedAt();
    }
}
