package com.example.newsFeed.feed.controller;

import com.example.newsFeed.aop.TrackTime;
import com.example.newsFeed.feed.dto.request.FeedCreateUpdateRequestDto;
import com.example.newsFeed.feed.dto.response.FeedCreateResponseDto;
import com.example.newsFeed.feed.dto.response.FeedFindAllResponseDto;
import com.example.newsFeed.feed.dto.response.FeedFindResponseDto;
import com.example.newsFeed.feed.dto.response.FeedUpdateResponseDto;
import com.example.newsFeed.feed.service.FeedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds")
public class FeedController {
    private final FeedService feedService;
    // 피드 생성
    @TrackTime
    @PostMapping
    public FeedCreateResponseDto feedCreateAPI(@RequestBody FeedCreateUpdateRequestDto feedCreateRequestDto,
                                               HttpServletRequest request) throws UnsupportedEncodingException {
        String token = request.getHeader("Authorization");
        if (token == null) {
            throw new IllegalArgumentException("토큰값을 입력해주세요.");
        }
        return feedService.feedCreateService(feedCreateRequestDto, token);
    }
    // 피드 수정
    @TrackTime
    @PutMapping("/{id}")
    public FeedUpdateResponseDto feedUpdateAPI(@PathVariable Long id,
                                               @RequestBody FeedCreateUpdateRequestDto feedCreateRequestDto,
                                               HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            throw new IllegalArgumentException("유저 아이디를 찾을 수 없습니다.");
        }
        FeedUpdateResponseDto feedUpdateResponseDto = feedService.feedUpdateService(feedCreateRequestDto, id, userId);
        return  feedUpdateResponseDto;
    }
    // 피드 다건 조회
    @GetMapping
    public List<FeedFindAllResponseDto> feedFindAllAPI() {
        List<FeedFindAllResponseDto> feedFindAllResponseDtoList = feedService.feedFindAllService();
        return feedFindAllResponseDtoList;
    }
    // 피드 단건 조회
    @GetMapping("/{id}")
    public FeedFindResponseDto feedFindAPI(@PathVariable Long id) {
        FeedFindResponseDto feedFindResponseDto = feedService.feedFindService(id);
        return feedFindResponseDto;
    }
    // 피드 삭제
    @DeleteMapping("/{id}")
    public void feedDeleteAPI(@PathVariable Long id, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        if (userId == null) {
            throw new IllegalArgumentException("유저 아이디를 찾을 수 없습니다.");
        }
        feedService.feedDeleteService(id, userId);
    }




}
