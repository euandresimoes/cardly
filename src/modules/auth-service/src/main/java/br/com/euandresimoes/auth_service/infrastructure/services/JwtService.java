package br.com.euandresimoes.auth_service.infrastructure.services;

import br.com.euandresimoes.auth_service.application.interfaces.IJwtService;
import br.com.euandresimoes.auth_service.domain.entity.UserEntity;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService implements IJwtService {

    @Value("${JWT.SECRET}")
    private String secret;

//    Tests
//    private final String secret = "secret12345";

    @Override
    public String generate(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("auth-service")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withClaim("email", user.getEmail())
                    .withClaim("displayName", user.getDisplayName())
                    .withExpiresAt(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
