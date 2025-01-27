package com.example.newsFeed.feed.dto.response;

import com.example.newsFeed.entity.Feed;
import lombok.Getter;

@Getter
public class FeedFindAllResponseDto {
    private Long id;
    private String title;
    private String content;

    public FeedFindAllResponseDto(Feed feed) {
        this.id = feed.getId();
        this.title = feed.getTitle();
        this.content = feed.getContent();
    }
}
