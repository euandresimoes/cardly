package br.com.euandresimoes.auth_service.application.interfaces;

import br.com.euandresimoes.auth_service.domain.entity.UserEntity;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

public interface IJwtService {
    String generate(UserEntity user);
}
