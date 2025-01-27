package com.example.newsFeed.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// Auditing 기능을 사용해서 자동으로 값을 매핑시키는 어노테이션
// 마무리로 application에 @EnableJpaAuditing 어노테이션 추가해서 작동시키자
@EntityListeners(AuditingEntityListener.class)
// BaseEntity를 상속한 엔티티들은 BaseEntity에 있는 멤버변수(속성)들을 모두 컬럼으로 인식하게 되도록하는 어노테이션.
@MappedSuperclass
@Getter
/*
public abstract class BaseEntity: 추상 클래스
abstract을 넣는 이유는 BaseEntity는 상속되는 엔티티이기 때문에 상속 받는 엔티티에서만 활용되도록 강제시키기 위해.
++ 혹시라도 독립적으로 사용될 수도 있는 실수를 방지하기 위함.
 */
public abstract class BaseEntity {

    // 속성(멤버변수)
//    @CreatedBy: 생성한 '사람'을 저장할때(나타내고 싶을 때)
    @CreatedDate
//    MySql에 저장될 createdAt의 속성들을 설정해주는 느낌.
//    updatable = false는 날짜 업데이트를 막는다. createdAt에 대한 setter가 없기 떄문에 설정 안해도 되긴 하지만 최소한의 안전장치인셈.
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

//    @LastModifiedBy: 수정한 '사람'을 저장할 때 (나타내고 싶을 때)
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

}
