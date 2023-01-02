package peaksoft.driverapp.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import peaksoft.driverapp.config.JwtConfig;
import peaksoft.driverapp.models.entities.AuthInfo;

import java.util.Date;

/**
 * @author B.Muhammed
 */
@Component
@AllArgsConstructor
public class JwtUtils {
    // dependency injection
    // JwtConfig
    private final JwtConfig jwtConfig;

    // token generator
    public String generateToken(Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(authInfo.getEmail())
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (jwtConfig.getExpirationDateAfterDays() * 8600000)))
                .compact();
    }

    // get username || email from token
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // token verifier
    public boolean verifyToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtConfig.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
