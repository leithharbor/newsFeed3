package com.example.newsFeed.user.dto.response;

import com.example.newsFeed.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

// Getter 어노테이션을 안넣었더니 포스트맨에서 406에러가 났다. 처음에 로그로 오류 찾아보려고 했는데 못찾았어요.
// ResponseDto에 Getter가 없으면 json 응답을 할수없다고 한다. 406에러는 '보통' ResponseDto의 문제.
@Getter
@AllArgsConstructor
public class UserCreateResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private String createMessage;

    public UserCreateResponseDto(String createMessage, User savedUser) {
        this.createMessage = createMessage;
        this.id = savedUser.getId();
        this.name = savedUser.getName();
        this.email = savedUser.getEmail();
        this.createdAt = savedUser.getCreatedAt();
    }

}
