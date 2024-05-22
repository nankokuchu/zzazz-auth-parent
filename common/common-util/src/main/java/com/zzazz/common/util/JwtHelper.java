package com.zzazz.common.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * ClassName: JwtHelper
 * Package: com.zzazz.common.util
 *
 * @Author: zzazz
 * @Create: 2024/5/20 - 9:29
 * @Description: JwtHelper
 * @Version: v1.0
 */
public class JwtHelper {

    // token有効時間
    private static final long tokenExpiration = 365L * 24 * 60 * 60 * 1000;
    // private_key
    private static final String tokenSignKey = "123456";

    // ユーザーIDとユーザー名とtokenを生成する
    public static String createToken(Long userId, String username) {
        return Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    // tokenからユーザーIDを取得
    public static Long getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userIdInteger = (Integer) claims.get("userId");
            return userIdInteger.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // tokenからusernameを取得
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // テスト
    // public static void main(String[] args) {
    //     String token = JwtHelper.createToken("1", "test");
    //     System.out.println(token);
    //
    //     String userId = JwtHelper.getUserId(token);
    //     System.out.println(userId);
    //
    //     String username = JwtHelper.getUsername(token);
    //     System.out.println(username);
    // }
}