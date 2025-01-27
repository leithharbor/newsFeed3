package com.example.newsFeed.user.service;

import com.example.newsFeed.config.PasswordEncoder;
import com.example.newsFeed.entity.User;
import com.example.newsFeed.auth.jwt.JwtUtils;
import com.example.newsFeed.user.dto.request.UserCreateRequestDto;
import com.example.newsFeed.user.dto.request.UserLogInRequestDto;
import com.example.newsFeed.user.dto.request.UserUpdateRequestDto;
import com.example.newsFeed.user.dto.response.*;
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
public class UserService {
    // 속성
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    // 기능
//    회원가입
    public UserCreateResponseDto userCreateService(UserCreateRequestDto userCreateRequestDto) {
        // 비크립트: 패스워드 엔코딩하기
        String bCryptPassword = passwordEncoder.encode(userCreateRequestDto.getPassword());
        // 이메일 검증(이메일 중복 방지 플래그 세우기)
        boolean flag = userRepository.existsByEmail(userCreateRequestDto.getEmail());
        // 이미 존재하는 이메일은 차단
        if (flag) {
            throw new RuntimeException("이미 존재하는 이메일 입니다.");
        }
        // 엔코디드 패스워드 User 객체에 넣기
        User user = new User(userCreateRequestDto.getName(),
                userCreateRequestDto.getEmail(),
                bCryptPassword);
        // 레포지에 저장
        User savedUser = userRepository.save(user);
        log.info("::: 유저 정보 레포지에 저장함");
        // 저장한 User 객체 ResponseDto에 담기
        UserCreateResponseDto userCreateResponseDto = new UserCreateResponseDto("회원가입 되었습니다.", savedUser);
        log.info("::: 기입된 내용 반환준비 완료");
        return  userCreateResponseDto;
    }
//    회원탈퇴
    public void userDeleteService(Long id) {
        // IllegalArgumentException은 잘못된 값이 전달된 경우의 예외처리.
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다." + " >>>>> " + id));
        log.info("::: 탈퇴시킬 회원 찾기");

        user.setDeleted(true);
        log.info("::: 회원탈퇴라고 했지만 사실은 유저 정보는 저장하고 있음");
        userRepository.save(user);
        log.info("::: 회원탈퇴라는 상태를 저장");
    }
//    회원정보 수정
    public UserUpdateResponseDto userUpdateService(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("::: 없는 사람!" + " >>>>> " + id));
        log.info("::: 수정시킬 회원 찾기");
        user.updateInfo(userUpdateRequestDto);
        User updatedUser = userRepository.save(user);
        UserUpdateResponseDto userUpdateResponseDto = new UserUpdateResponseDto(updatedUser, "수정이 완료되었습니다.");
        return userUpdateResponseDto;
    }
//    회원 단건 조회 (일단은 아이디 조회)
    public UserFindResponseDto userFindService(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " <<<<< " + "찾을 수 없습니다."));
        UserFindResponseDto userFindResponseDto = new UserFindResponseDto(user);
        return userFindResponseDto;
    }
//    회원 다건 조회 (일단은 전체 조회)
    public List<UserFindAllResponseDto> userFindAllService() {
        // 레포지에서 전부 찾아와서 유저리스트에 담기
        List<User> userList = userRepository.findAll();
        // 유저리스트 스트림사용. 스트림은 데이터를 순차적으로 처리할 수 있는 기능.
        // for (User user : userList) 랑 같은 느낌인듯
        List<UserFindAllResponseDto> userFindAllResponseDtoList = userList
                .stream()
                // map은 타입을 변환시켜준다.
                // userList는 User 타입인데 이걸 아래 적은 UserFindAllResponseDto 타입으로 변환시킨다.
                .map(UserFindAllResponseDto::new)
                // collect는 map에서 변환된 데이터들을 리스트형태로 모은다.
                .collect(Collectors.toList());
        /*
         * .stream().map().collect(Collectors.toList())은 전체 조회에서는 한 세트라고 볼 수 있다.
         * 정리하자면 위에서 찾은 User타입 데이터들을 순차적으로 짚으면서 dto로 타입변경해주고 그걸 리스트로 담아서 나온다.
         * 각 순서와 과정을 단어들이 비교적 직관적으로 표현하고 있어 이해하기 쉬웟다.
         * 공장의 제품 생산 과정과 유사하다.
          */
        return userFindAllResponseDtoList;
    }
//    회원 로그인
    public String userLoginService(UserLogInRequestDto userLoginRequestDto) throws UnsupportedEncodingException {
        // 이메일로 유저 찾기
        User user = userRepository.findByEmail(userLoginRequestDto.getEmail())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        // 비밀번호 검증
        boolean passwordMatches = passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword());
        if (!passwordMatches) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        log.info("비밀번호 일치함");
        // 생성된 토큰을 가져왔다
        String token = jwtUtils.createToken(user.getId());
        log.info("생성된 토큰 겟");
        // 토큰 반환
        return token;
    }

}
