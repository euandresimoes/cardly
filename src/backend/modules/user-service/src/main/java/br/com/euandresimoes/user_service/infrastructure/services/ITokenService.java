package br.com.euandresimoes.user_service.infrastructure.services;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface ITokenService {
    DecodedJWT decode(String token);
    String extract(String authHeader);
}