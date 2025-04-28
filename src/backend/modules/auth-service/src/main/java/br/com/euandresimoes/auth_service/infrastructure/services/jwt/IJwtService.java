package br.com.euandresimoes.auth_service.infrastructure.services.jwt;

import br.com.euandresimoes.auth_service.domain.entities.UserEntity;

public interface IJwtService {
    String generate(UserEntity user);
}
