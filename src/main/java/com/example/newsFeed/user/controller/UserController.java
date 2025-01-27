package com.example.newsFeed.user.controller;

import com.example.newsFeed.auth.jwt.JwtUtils;
import com.example.newsFeed.user.dto.request.UserCreateRequestDto;
import com.example.newsFeed.user.dto.request.UserLogInRequestDto;
import com.example.newsFeed.user.dto.request.UserUpdateRequestDto;
import com.example.newsFeed.user.dto.response.*;
import com.example.newsFeed.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/newsFeeds")
public class UserController {
    // 속성
    private final UserService userService;
    private final JwtUtils jwtUtils;
    // 기능
//    회원가입
    @PostMapping("/signUp")
    public ResponseEntity<UserCreateResponseDto> userCreateAPI(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        UserCreateResponseDto usersCreateResponseDto = userService.userCreateService(userCreateRequestDto);
        return new ResponseEntity<>(usersCreateResponseDto, HttpStatus.OK);
    }
//    회원탈퇴
    @DeleteMapping("/{id}")
    public ResponseEntity<String> userDeleteAPI(@PathVariable Long id) {
        userService.userDeleteService(id);
        return ResponseEntity.ok("그동안 저희 서비스를 이용해주셔서 감사합니다.");
    }
//    회원정보 수정
    @PutMapping("/{id}")
    public UserUpdateResponseDto userUpdateAPI(@PathVariable Long id,
                                               @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        UserUpdateResponseDto userUpdateResponseDto = userService.userUpdateService(id, userUpdateRequestDto);
        return userUpdateResponseDto;
    }
//    회원 단건 조회 (일단은 아이디 조회)
    @GetMapping("/{id}")
    public UserFindResponseDto userFindAPI(@PathVariable Long id) {
        UserFindResponseDto userFindResponseDto = userService.userFindService(id);
        return userFindResponseDto;
    }
//    회원 다건 조회(일단은 전체 조회;)
    @GetMapping
    public List<UserFindAllResponseDto> userFindAllAPI() {
        List<UserFindAllResponseDto> userFindAllResponseDtos = userService.userFindAllService();
        return userFindAllResponseDtos;
    }
//    로그인
    @PostMapping("/signIn")
    public String userLogInAPI(
            @RequestBody UserLogInRequestDto userLoginRequestDto) throws UnsupportedEncodingException {
        log.info("로그인 시도");
        return userService.userLoginService(userLoginRequestDto);
    }
}

