package com.pjt.ssafybuddy.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class JWTUtil {
    @Value("${jwt.secret}")
    private String salt;
    //Access 토큰 만료 시간 4분 -> 일단 이렇게 설정 나중에 수정 가능
    private long accessTokenExpireTime = 240000;
    //Refresh 토큰 만료시간 두시간 -> 이것도 수정 가능
    private long refreshTokenExpireTime = 7200000;
    //secret code 잘 load하는 지 확인
    @PostConstruct
    public void logSecret() {
        log.info("JWT Secret loaded: {}", salt);
    }
    //access token 설정
    public String createAccessToken(String userId){
        return create(userId, "access-token", accessTokenExpireTime);
    }
    //refresh token 설정 - access token에 비해 길게 설정한다.
    public String createRefreshToken(String userId){
        return create(userId, "refresh-token", refreshTokenExpireTime);
    }
    public String create(String userId, String subject, long expireTime){
        Claims claims = Jwts.claims().setSubject(subject) //토큰의 제목 설정 ex) access-token, refresh-token
                .setIssuedAt(new Date()) //생성일 설정
                .setExpiration(new Date(System.currentTimeMillis() + expireTime)); // 만료일 설정
        claims.put("userId", userId);
        String jwt = Jwts.builder().setHeaderParam("typ", "JWT").setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.generateKey()) //header 설정, 토큰의 타입, 해쉬 알고리즘 정보 세팅
                .compact(); //직렬화 처리
        return jwt;
    }
    //Signature 설정에 들어갈 key 생성
    public byte[] generateKey(){
        byte[] key = null;
        try{
            key = salt.getBytes("UTF-8");
            System.out.println(key);
        }catch (UnsupportedEncodingException e){
            if(log.isInfoEnabled()){
                e.printStackTrace();
            }else{
                log.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }
        return key;
    }
    //토큰이 재대로 생성되었는지 확인하고 문제가 있다면 UnauthorizedException을 발생
    public boolean checkToken(String token){
        try{
            Jws<Claims> claimsJwts = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
            log.debug("Claims : {}", claimsJwts);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
    public boolean checkExpired(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
        if(claimsJws.getBody().getExpiration().before(new Date())){
            return false;
        }
        return true;
    }
    //토큰 유효성 검사용
    public String getUserId(String authorization){
        Jws<Claims> claimsJws = null;
        try{
            claimsJws = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(authorization);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        Map<String, Object> value = claimsJws.getBody();
        log.info("value : {}", value);
        return (String) value.get("userId");
    }
}
