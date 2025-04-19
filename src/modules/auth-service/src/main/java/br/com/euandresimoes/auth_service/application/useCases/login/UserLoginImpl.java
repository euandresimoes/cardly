package br.com.euandresimoes.auth_service.application.useCases;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserLoginRequest;
import br.com.euandresimoes.auth_service.application.dtos.responses.UserLoginResponse;
import br.com.euandresimoes.auth_service.application.exceptions.InvalidCredentialsException;
import br.com.euandresimoes.auth_service.application.exceptions.UserNotFoundException;
import br.com.euandresimoes.auth_service.domain.entities.UserEntity;
import br.com.euandresimoes.auth_service.domain.repositories.UserRepository;
import br.com.euandresimoes.auth_service.infrastructure.services.JwtService;
import br.com.euandresimoes.auth_service.infrastructure.services.PasswordHashService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserLoginUseCase {

    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final PasswordHashService passwordHashService;

    public UserLoginUseCase(UserRepository userRepo, JwtService jwtService, PasswordHashService passwordHashService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.passwordHashService = passwordHashService;
    }

    public UserLoginResponse execute(@Valid UserLoginRequest data) {
        UserEntity user = userRepo.findByEmail(data.email());

        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        if (!passwordHashService.matches(data.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials.");
        }

        String token = jwtService.generate(user);

        return new UserLoginResponse(token);
    }
}
