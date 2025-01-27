package com.example.newsFeed.entity;

import com.example.newsFeed.feed.dto.request.FeedCreateUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Table(name = "feed")
@Entity
public class Feed extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    @Setter
    private boolean isDeleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Feed () {}

    public Feed(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void updateFeed(FeedCreateUpdateRequestDto feedCreateRequestDto) {
        if (feedCreateRequestDto.getTitle() != null && !feedCreateRequestDto.getTitle().isEmpty()) {
            this.title = feedCreateRequestDto.getTitle();
        }
         /*
         내용 수정이 안돼서 한참 찾다가 !feedCreateRequestDto.getContent().isEmpty() 이 부분에서
         맨 앞에 !가 빠져서 공백일때만 반환되도록 설정이 되어있었다...
          */
        if (feedCreateRequestDto.getContent() != null && !feedCreateRequestDto.getContent().isEmpty()) {
            this.content = feedCreateRequestDto.getContent();
        }
    }
}
