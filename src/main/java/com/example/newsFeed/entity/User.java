package com.example.newsFeed.entity;

import com.example.newsFeed.user.dto.request.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 아래 어노테이션으로 엔티티의 기본생성자를 protected로 생성시킬수있다. 지금은 연습을 위해서 알아두기만 하자.
 * @NoArgsConstructor(access = AccessLevel.PROTECTED)
  */


@Getter
@Table(name = "user")
@Entity
// BaseEntity를 상속 받는다는 뜻으로 extends 사용
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Setter // isDeleted boolean 기능을 쓰기 위해 세터 적용. Service에서 setDelete로 상태 변경.
    private boolean isDeleted = false;

    protected User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    // 기능
    public void updateInfo(UserUpdateRequestDto userUpdateRequestDto) {
        if (userUpdateRequestDto.getName() != null && !userUpdateRequestDto.getName().isEmpty()) {
            this.name = userUpdateRequestDto.getName();
        }
        if (userUpdateRequestDto.getEmail() != null && !userUpdateRequestDto.getEmail().isEmpty() && userUpdateRequestDto.getEmail().contains("@")) {
            this.email = userUpdateRequestDto.getEmail();
        }
        if (userUpdateRequestDto.getPassword() != null && !userUpdateRequestDto.getPassword().isEmpty()) {
            this.password = userUpdateRequestDto.getPassword();
        }
    }


}
