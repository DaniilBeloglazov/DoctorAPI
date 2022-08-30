package com.example.doctorapiv2.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtils {
    @Value("${whiteyesx.app.jwt.secret}")
    private String jwtSecret;
    @Value("${whiteyesx.app.jwt.accessexp}")
    private Long expTimeAccess;
    @Value("${whiteyesx.app.jwt.refreshexp}")
    private Long expTimeRefresh;

    public Tokens generateTokens(String username) {
        val accesstoken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(expTimeAccess).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        val refreshtoken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(expTimeRefresh).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        return new Tokens(accesstoken, refreshtoken);
    }
    public String parseJwtFromRequest(HttpServletRequest request){
        return request.getHeader("accesstoken");
    }
    public String fetchUsernameFromJWT(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
