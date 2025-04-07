package br.com.euandresimoes.auth_service.application.useCases;

import br.com.euandresimoes.auth_service.application.dtos.request.UserLoginRequest;
import br.com.euandresimoes.auth_service.application.dtos.response.UserLoginResponse;
import br.com.euandresimoes.auth_service.application.exceptions.InvalidCredentialsException;
import br.com.euandresimoes.auth_service.application.exceptions.UserNotFoundException;
import br.com.euandresimoes.auth_service.domain.entity.UserEntity;
import br.com.euandresimoes.auth_service.domain.repository.UserRepository;
import br.com.euandresimoes.auth_service.infrastructure.services.JwtService;
import br.com.euandresimoes.auth_service.infrastructure.services.PasswordHashService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginUseCaseTest {

    private UserRepository userRepo;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private PasswordHashService passwordHashService;
    private UserLoginUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepository.class);
        jwtService = new JwtService();
        passwordEncoder = new BCryptPasswordEncoder();
        passwordHashService = new PasswordHashService(passwordEncoder);
        useCase = new UserLoginUseCase(userRepo, jwtService, passwordHashService);
    }

    @Test
    void shouldLoginAndReturnAccessToken() {
        UserLoginRequest request = new UserLoginRequest("johndoe@example.com", "password123");

        UserEntity mockUser = new UserEntity();
        mockUser.setEmail(request.email());
        mockUser.setPassword(passwordHashService.encode(request.password()));

        Mockito.when(userRepo.findByEmail(request.email())).thenReturn(mockUser);

        var response = useCase.execute(request);

        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.accessToken().isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        UserLoginRequest request = new UserLoginRequest("johndoe@example.com", "password123");

        Mockito.when(userRepo.findByEmail(request.email())).thenReturn(null);

        Assertions.assertThrows(
                UserNotFoundException.class,
                () -> {
                    useCase.execute(request);
                }
        );
    }

    @Test
    void shouldThrowExceptionWhenPasswordDoesNotMatches() {
        UserLoginRequest request = new UserLoginRequest("johndoe@example.com", "password123");

        UserEntity mockUser = new UserEntity();
        mockUser.setEmail(request.email());
        mockUser.setPassword(passwordHashService.encode("password12345"));

        Mockito.when(userRepo.findByEmail(request.email())).thenReturn(mockUser);

        Assertions.assertThrows(
                InvalidCredentialsException.class,
                () -> {
                    useCase.execute(request);
                }
        );
    }
}