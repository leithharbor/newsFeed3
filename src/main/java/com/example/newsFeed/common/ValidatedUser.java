package com.example.newsFeed.common;

import com.example.newsFeed.auth.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatedUser {
    // 속성
    private final JwtUtils jwtUtils;
    // 생(략)
    // 기능

}
