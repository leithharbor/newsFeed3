package com.example.newsFeed.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtUtils {

    // SECRET 의 경우 스프링설정파일(application.properties) 에서 주입받아 활용가능합니다.
    private static final String SECRET = "pikachu-raichu-charmander-squirtle";
    private static final String BEARER_PREFIX = "Digimon";


    public String createToken(Long Id) throws UnsupportedEncodingException {
        // 1. 토큰 서명에 활용될 알고리즘 설정
        Algorithm algorithm = Algorithm.HMAC256(SECRET); // Header

        // 2. 토큰 생성
        String token = JWT.create()
                .withIssuer("이상구")
                .withSubject(Id.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .sign(algorithm); // 서명
        return token;
    }

    public Long extractUserIdFromBearerToken(String bearerToken) throws UnsupportedEncodingException {
        // 1. 토큰에 활용할 알고리즘
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // 2. Bearer 토큰 추출
        String token = bearerToken.substring(BEARER_PREFIX.length()).trim();

        // 3. 토큰 검증
        DecodedJWT decodedToken = JWT.require(algorithm)
                .withIssuer("이상구")
                .build()
                .verify(token);

        // 4. 토큰에서 원하는 정보 추출
        System.out.println("subject: " + decodedToken.getSubject());
        System.out.println("issued At: " + decodedToken.getIssuedAt());
        System.out.println("expires At: " + decodedToken.getExpiresAt());

        // 5. 학생 식별자 반환
        String Id = decodedToken.getSubject();
        return Long.parseLong(Id);
    }
}
