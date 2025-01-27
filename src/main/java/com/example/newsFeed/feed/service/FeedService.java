package com.example.newsFeed.feed.service;

import com.example.newsFeed.auth.jwt.JwtUtils;
import com.example.newsFeed.entity.Feed;
import com.example.newsFeed.entity.User;
import com.example.newsFeed.feed.dto.request.FeedCreateUpdateRequestDto;
import com.example.newsFeed.feed.dto.response.FeedCreateResponseDto;
import com.example.newsFeed.feed.dto.response.FeedFindAllResponseDto;
import com.example.newsFeed.feed.dto.response.FeedFindResponseDto;
import com.example.newsFeed.feed.dto.response.FeedUpdateResponseDto;
import com.example.newsFeed.feed.repository.FeedRepository;
import com.example.newsFeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    // 피드 생성
    public FeedCreateResponseDto feedCreateService (FeedCreateUpdateRequestDto feedCreateRequestDto,
                                                    String token) throws UnsupportedEncodingException {
        Long userId = jwtUtils.extractUserIdFromBearerToken(token);
        User validatedUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Feed feed = new Feed(feedCreateRequestDto.getTitle(), feedCreateRequestDto.getContent(), validatedUser);
        Feed savedFeed = feedRepository.save(feed);
        FeedCreateResponseDto feedCreateResponseDto = new FeedCreateResponseDto(savedFeed);
        return feedCreateResponseDto;
    }
    // 피드 수정
    public FeedUpdateResponseDto feedUpdateService(FeedCreateUpdateRequestDto feedCreateRequestDto, Long id, Long userId) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드: {" + id + "}"));
        User user = feed.getUser();
        if (user == null || !user.getId().equals(userId)) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
        feed.updateFeed(feedCreateRequestDto);
        Feed updatedFeed = feedRepository.save(feed);
        FeedUpdateResponseDto feedUpdateResponseDto = new FeedUpdateResponseDto(updatedFeed);
        return feedUpdateResponseDto;
    }
    // 피드 다건 조회
    public List<FeedFindAllResponseDto> feedFindAllService() {
        List<Feed> feedList = feedRepository.findAll();
        List<FeedFindAllResponseDto> feedFindAllResponseDtoList = feedList
                .stream()
                .map(FeedFindAllResponseDto::new)
                .collect(Collectors.toList());
        return feedFindAllResponseDtoList;
    }
    // 피드 단건 조회
    public FeedFindResponseDto feedFindService(Long id) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드: {" + id + "}"));
        FeedFindResponseDto feedFindResponseDto = new FeedFindResponseDto(feed);
        return feedFindResponseDto;
    }
    // 피드 삭제
    public void feedDeleteService(Long id, Long userId) {
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드: {" + id + "}"));
        User user = feed.getUser();
        if (user == null || !user.getId().equals(userId)) {
            throw new RuntimeException("권한이 없습니다.");
        }
        feed.setDeleted(true);
        feedRepository.save(feed);
        log.info("삭제 완료");
    }
}
