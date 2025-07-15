package com.example.campusborrowing.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    // 静态密钥 用于加密
    private static final String SCERTE = "campus-market-key";
    // 过期时间
    private static final long EXPIRE = 1000 * 60 * 60 * 24;

    // generato token
    public  static String createToken(Integer userId) {
        return Jwts.builder()
                .setSubject(userId.toString())  // token 里存了用户 id的信息
                .setIssuedAt(new Date(System.currentTimeMillis()+EXPIRE)) // 过期时间
                .signWith(SignatureAlgorithm.HS256,SCERTE)  // 加密方式
                .compact();
    }
    // obtain userId from token
    public static Integer getUserId(String token) {
        String subject = Jwts.parser()
                .setSigningKey(SCERTE)
                .parseClaimsJws(token)
                .getBody().getSubject();
        return Integer.parseInt(subject);
    }
}