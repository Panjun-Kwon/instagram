package com.example.instagram.config.jwt;

import com.example.instagram.common.exception.Response;
import com.example.instagram.common.exception.ErrorCode;
import com.example.instagram.domain.user.AuthUser;
import com.example.instagram.domain.user.Role;
import com.example.instagram.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.security.Key;
import java.util.Date;

import static com.example.instagram.common.exception.ErrorCode.*;

@Slf4j
@Component
public class JwtUtils {
    private final Key key;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JwtUtils(@Value("${jwt.app.jwtSecretKey}")String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessTokenFromLoginUser(AuthUser authUser) {
        return Jwts.builder()
                .claim("id", authUser.getUser().getId())
                .claim("email",authUser.getUser().getEmail())
                .claim("username", authUser.getUser().getUsername())
                .claim("nickname",authUser.getUser().getNickname())
                .claim("role", authUser.getUser().getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + JwtProperties.ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String parseJwtToken(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING);
        if (StringUtils.hasText(token) && token.startsWith(JwtProperties.TOKEN_PREFIX)) {
            token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        }
        return token;
    }

    public boolean validationJwtToken(String token, HttpServletResponse response) throws IOException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            setResponse(response, INVALID_JWT_TOKEN, e);
        } catch (ExpiredJwtException e) {
            setResponse(response, EXPIRED_ACCESS_TOKEN, e);
        } catch (UnsupportedJwtException e) {
            setResponse(response, UNSUPPORTED_JWT_TOKEN, e);
        }
        return false;
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode, Exception e) throws IOException {
        log.error("error message {}", errorCode.getMessage(), e);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Response<Void> fail = Response.error(errorCode.getStatus().toString(), errorCode.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(fail));
    }

    public AuthUser verify(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Long id = claims.get("id", Long.class);
        String email = claims.get("email", String.class);
        String username = claims.get("username", String.class);
        String nickname = claims.get("nickname", String.class);
        String role = claims.get("role", String.class);

        User user = User.builder()
                .id(id)
                .email(email)
                .username(username)
                .nickname(nickname)
                .role(Role.valueOf(role))
                .build();

        return new AuthUser(user);
    }

}
