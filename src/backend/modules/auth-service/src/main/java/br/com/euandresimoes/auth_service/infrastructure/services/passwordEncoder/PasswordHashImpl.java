package br.com.euandresimoes.auth_service.infrastructure.services.passwordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordHashImpl implements IPasswordHasher {
    private final PasswordEncoder passwordEncoder;

    public PasswordHashImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String raw) {
        return passwordEncoder.encode(raw);
    }

    @Override
    public Boolean matches(String raw, String hash) {
        return passwordEncoder.matches(raw, hash);
    }
}
