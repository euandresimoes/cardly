package br.com.euandresimoes.auth_service.shared.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHasher {
    private final PasswordEncoder passwordEncoder;

    public PasswordHasher(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encode(String raw) {
        return passwordEncoder.encode(raw);
    }

    public Boolean matches(String raw, String hash) {
        return passwordEncoder.matches(raw, hash);
    }
}
