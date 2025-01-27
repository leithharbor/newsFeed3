package com.example.newsFeed.user.repository;

import com.example.newsFeed.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 같은 이메일이 존재하는지에 대해 yes or no 대답
    // findBy보다 existBy가 더 적합.
    // 찾아서 작업하는게 아니라 단순 비교이기 때문에 존재하는지 여부만 알아보면 됨.
    boolean existsByEmail(String email);
    // 이메일로 유저 찾기 기능 만들기
    Optional<User> findByEmail (String email);
}
