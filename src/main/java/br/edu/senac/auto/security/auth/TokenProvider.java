package br.edu.senac.auto.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenProvider {

    private static final long EXPIRATION_TIME = 1000 * 60 * 30; // 30 minutes
    private static final String SECRET = "ArrozComFeijao123";
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String user) {
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + getToken(user));
        response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HEADER_STRING);
    }

    public static String getToken(String user) {
        return Jwts.builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static Optional<String> getUserFromToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();

            Instant expiration = body.getExpiration().toInstant();

            if (expiration.isBefore(Instant.now())) {
                return Optional.empty();
            } else {
                return Optional.of(body.getSubject());
            }
        }

        return Optional.empty();
    }
}
