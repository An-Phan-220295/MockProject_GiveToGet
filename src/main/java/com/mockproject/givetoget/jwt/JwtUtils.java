package com.mockproject.givetoget.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${custom.token.key}")
    private String secretKey;
    private long expiredTime = 8 * 60 * 60 * 1000;

    public String generateToken(UserInfo data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Date date = new Date();
        long newDateMilis = date.getTime() + expiredTime;
        Date newExpiredDate = new Date(newDateMilis);

        return Jwts.builder()
                .subject(data.getEmail())
                .claim("role", data.getRole())
                .setIssuedAt(date)
                .expiration(newExpiredDate)
                .signWith(key)
                .compact();
    }

    public UserInfo parserToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        try {
            Claims data = Jwts.parser()
                    .verifyWith(key).build()
                    .parseSignedClaims(token)
                    .getPayload();

            return UserInfo.builder()
                    .email(data.getSubject())
                    .role(data.get("role").toString())
                    .build();
        } catch (ExpiredJwtException e) {
            System.err.println("Token đã hết hạn: " + e.getMessage());
            throw new IllegalArgumentException("Token đã hết hạn");
        } catch (SignatureException e) {
            System.err.println("Chữ ký token không hợp lệ: " + e.getMessage());
            throw new IllegalArgumentException("Chữ ký token không hợp lệ");
        } catch (Exception e) {
            System.err.println("Lỗi khi parse token: " + e.getMessage());
            throw new IllegalArgumentException("Token không hợp lệ");
        }
    }
}
