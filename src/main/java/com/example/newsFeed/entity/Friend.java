package com.example.newsFeed.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "friend")
@Entity
public class Friend extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User follower;  // 친구 추가를 하는 주체. 친구를 만들고 싶은 사람.
    @ManyToOne(fetch = FetchType.LAZY)
    private User following; // 내가 친추하려는 대상. 친구 추가 당하는 사람.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private FriendRequest friendRequest = FriendRequest.PENDING; // 기본 상태는 대기 상태

    protected Friend () {}

}
