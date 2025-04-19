package br.com.euandresimoes.auth_service.infrastructure.services.passwordEncoder;

public interface IPasswordHasher {
    String encode(String raw);
    Boolean matches(String raw, String hash);
}
