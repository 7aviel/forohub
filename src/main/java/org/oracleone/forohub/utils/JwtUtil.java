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

    private final String SECRET_KEY = "7db575312a9f85409be9ca4146b5478296d4d45bc53f2c6648280b8479fb01db9fcd3b2aa6407c50fa1226ad4602b58cfe6e2063b7945dadb7465bd58f06f42e3be9e0651f95096865649e4c4531d674a28175fb2ca3ebe445cd83c90690566cda2ab31b56322f938bbcd89ff3c0d93c76ffeb6b5427c37057ab3ec9671cd8101bcbe571704c22cc7b7068292118987a365600eae409f4a80b370bfad5cf88ebb4c96bdcef3475e21ff591f37420a696c5e32d07907047d235b78980b2082c4ea0749117e4fada76803556b23663545d8e567cdd7cf61730ae973150cf632601f11da5731b3eb5a4a769c630afd158482458554d1013fee6dd2e426e4fee98e9";

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

