package br.com.euandresimoes.auth_service.application.useCases;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserLoginRequest;
import br.com.euandresimoes.auth_service.application.exceptions.InvalidCredentialsException;
import br.com.euandresimoes.auth_service.application.exceptions.UserNotFoundException;
import br.com.euandresimoes.auth_service.application.useCases.login.UserLoginImpl;
import br.com.euandresimoes.auth_service.domain.entities.UserEntity;
import br.com.euandresimoes.auth_service.domain.repositories.UserRepository;
import br.com.euandresimoes.auth_service.infrastructure.services.jwt.JwtServiceImpl;
import br.com.euandresimoes.auth_service.infrastructure.services.passwordEncoder.PasswordHashImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserLoginImplTest {

    private UserRepository userRepo;
    private JwtServiceImpl jwtServiceImpl;
    private PasswordEncoder passwordEncoder;
    private PasswordHashImpl passwordHashImpl;
    private UserLoginImpl useCase;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepository.class);
        jwtServiceImpl = new JwtServiceImpl();
        passwordEncoder = new BCryptPasswordEncoder();
        passwordHashImpl = new PasswordHashImpl(passwordEncoder);
        useCase = new UserLoginImpl(userRepo, jwtServiceImpl, passwordHashImpl);
    }

    @Test
    void shouldLoginAndReturnAccessToken() {
        UserLoginRequest request = new UserLoginRequest("johndoe@example.com", "password123");

        UserEntity mockUser = new UserEntity();
        mockUser.setEmail(request.email());
        mockUser.setPassword(passwordHashImpl.encode(request.password()));

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
        mockUser.setPassword(passwordHashImpl.encode("password12345"));

        Mockito.when(userRepo.findByEmail(request.email())).thenReturn(mockUser);

        Assertions.assertThrows(
                InvalidCredentialsException.class,
                () -> {
                    useCase.execute(request);
                }
        );
    }
}