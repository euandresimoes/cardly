package br.com.euandresimoes.auth_service.application.useCases.login;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserLoginRequest;
import br.com.euandresimoes.auth_service.application.dtos.responses.UserLoginResponse;
import br.com.euandresimoes.auth_service.application.exceptions.InvalidCredentialsException;
import br.com.euandresimoes.auth_service.application.exceptions.UserNotFoundException;
import br.com.euandresimoes.auth_service.domain.entities.UserEntity;
import br.com.euandresimoes.auth_service.domain.repositories.UserRepository;
import br.com.euandresimoes.auth_service.infrastructure.services.jwt.JwtServiceImpl;
import br.com.euandresimoes.auth_service.infrastructure.services.passwordEncoder.PasswordHashImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserLoginImpl implements IUserLogin {

    private final UserRepository userRepo;
    private final JwtServiceImpl jwtServiceImpl;
    private final PasswordHashImpl passwordHashImpl;

    public UserLoginImpl(UserRepository userRepo, JwtServiceImpl jwtServiceImpl, PasswordHashImpl passwordHashImpl) {
        this.userRepo = userRepo;
        this.jwtServiceImpl = jwtServiceImpl;
        this.passwordHashImpl = passwordHashImpl;
    }

    @Override
    public UserLoginResponse execute(UserLoginRequest data) {
        UserEntity user = userRepo.findByEmail(data.email());

        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        if (!passwordHashImpl.matches(data.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials.");
        }

        String token = jwtServiceImpl.generate(user);

        return new UserLoginResponse(token);
    }
}
