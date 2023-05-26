package org.example.SecureAuthentication.Config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtUtil {
        private static final String SECRET_KEY = "your-secret-key";
        private static final long EXPIRATION_TIME = 86400000; // 24 hours

        public String generateToken(String username) {
            Map<String, Object> claims = new HashMap<>();
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
        }

        public boolean validateToken(String token, String username) {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject().equals(username) && !isTokenExpired(claims);
        }

        private boolean isTokenExpired(Claims claims) {
            Date expirationDate = claims.getExpiration();
            return expirationDate.before(new Date());
        }
}
