package br.com.euandresimoes.user_service.infrastructure.services;

import br.com.euandresimoes.user_service.application.exceptions.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ITokenService{
    @Override
    public DecodedJWT decode(String token) {
        return JWT.decode(token);
    }

    @Override
    public String extract(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidTokenException("Invalid token.");
        }

        return authHeader.replace("Bearer ", "");
    }
}