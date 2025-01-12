package org.oracleone.forohub.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "8e58507f5430355cec93fa1266f3fbe8686fe2fcfcb6819e3e8960f84330260650c50485b5ae98de7c66c5376d41b9e5656fac588a974dc51cc40f03520a7a4e4df572992b83fb39aeceef5ff04267c1dbeb0fa8a06ee22307f18dac8a2c5d6968e63b4d893abdb9894f5a0461165cae991585e69f56fb035377091b4ea5629efe1f1f35a12f4dbb7b67052f57f79fe37a98f8438fa92c88813ef53f39586564d08e616b7a74344ade0967a9cc7fa5f3d19020a6ea5c5df5841b4fed960274aa46e51d2e981eac7c36a3e8c9a1862d2c7efbadc2d423ca5275f677a20f9917dd39af7aa04f5e1202ca2fb2aaac195a788877f6c8bde760bd972216318b743e887f294e259859a798af424ba18fd80197bce3bfc3d9c0627d302f1eba1780a2ff";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

