package com.example.newsFeed.feed.dto.response;

import com.example.newsFeed.entity.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedUpdateResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime updatedAt;

    public FeedUpdateResponseDto(Feed updatedFeed) {
        this.id = updatedFeed.getId();
        this.title = updatedFeed.getTitle();
        this.content = updatedFeed.getContent();
        this.updatedAt = updatedFeed.getUpdatedAt();
    }
}
