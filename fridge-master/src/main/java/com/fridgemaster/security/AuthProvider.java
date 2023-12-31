package com.fridgemaster.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fridgemaster.dto.UserDTO;
import com.fridgemaster.service.UserService;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class AuthProvider {


    private final Key secretKey = Keys.hmacShaKeyFor("zdtlD3JK56m6wTTgsNFhqzjqPzdtlD3JK56m6wTTgsNFhqzjqP".getBytes());

    private final UserService userService;

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(String.valueOf(secretKey));
        return com.auth0.jwt.JWT.create()
                .withSubject(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(String.valueOf(secretKey));

        JWTVerifier verifier = com.auth0.jwt.JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UserDTO user = userService.findByLogin(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

}
