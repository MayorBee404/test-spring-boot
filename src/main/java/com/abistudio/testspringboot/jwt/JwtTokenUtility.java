package com.abistudio.testspringboot.jwt;


import com.abistudio.testspringboot.user.UserInfo;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenUtility {

    @Value("${app.jwt.issuer-name}")
    private String ISSUER_NAME;

    @Value("${app.jwt.period-of-validity}")
    private long PERIOD_OF_VALIDITY;

    @Value("${app.jwt.secret}")
    private String JWT_SECRET_KEY;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtility.class);

    public String generateAccessToken(UserInfo user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(ISSUER_NAME)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+PERIOD_OF_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();
    }
    public boolean validateAccessToken(String accessToken){
        if (parseClaims(accessToken) != null){
            return true;
        }else{
            return false;
        }
    }

    public String getUsername(String accessToken){
        return getSubject(accessToken);
    }
    private String getSubject(String accessToken){
        return parseClaims(accessToken).getBody().getSubject();
    }
    private Jws<Claims> parseClaims(String accessToken){
        try {
            return Jwts.parser()
                    .setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(accessToken);
        }catch (ExpiredJwtException ex){
            LOGGER.error("JWT expired",ex.getMessage());
        }catch (IllegalArgumentException ex){
            LOGGER.error("Token is null, empty or only whitespace",ex.getMessage());
        }catch (MalformedJwtException ex){
            LOGGER.error("Jwt is Invalid", ex);
        }catch (SignatureException ex){
            LOGGER.error("Signature Validation failed");
        }
        return null;
    }
}
