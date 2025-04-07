package br.com.euandresimoes.auth_service.application.interfaces;

public interface IPasswordHasher {
    String encode(String raw);
    Boolean matches(String raw, String hash);
}
