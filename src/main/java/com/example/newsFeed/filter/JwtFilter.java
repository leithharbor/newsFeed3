package com.example.newsFeed.filter;

import com.example.newsFeed.auth.jwt.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilter implements Filter {
    private final JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 1. Request 헤더에서 token 받아오기
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String token = httpRequest.getHeader("Authorization");
        if (token != null) {
            try {
                Long userId = jwtUtils.extractUserIdFromBearerToken(token);
                request.setAttribute("userId", userId);
            } catch (Exception e) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증되지 않은 토큰");
                return;
            }
        }
        // 2. 토큰 검증

        // 3. 유저 추출


        // 4. 요청 객체에 유저 정보 추가해주기
//        servletRequest.setAttribute("userId", userId);

        // 3. FilterChain
        filterChain.doFilter(request, response);
    }
}
