package br.com.euandresimoes.auth_service.application.useCases;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserRegisterRequest;
import br.com.euandresimoes.auth_service.application.exceptions.EmailAlreadyInUseException;
import br.com.euandresimoes.auth_service.application.useCases.register.UserRegisterImpl;
import br.com.euandresimoes.auth_service.domain.entities.UserEntity;
import br.com.euandresimoes.auth_service.domain.repositories.UserRepository;
import br.com.euandresimoes.auth_service.infrastructure.services.passwordEncoder.PasswordHashImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserRegisterImplTest {

    private UserRepository userRepo;
    private PasswordHashImpl passwordHash;
    private UserRegisterImpl useCase;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepository.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        passwordHash = new PasswordHashImpl(passwordEncoder);
        useCase = new UserRegisterImpl(userRepo, passwordHash);
    }

    @Test
    void shouldRegisterUserWithHashedPassword() {
        UserRegisterRequest request = new UserRegisterRequest("johndoe", "johndoe@example.com", "john@12345");

        Mockito.when(userRepo.findByEmail(request.email())).thenReturn(null);
        useCase.execute(request);

        Mockito.verify(userRepo).save(Mockito.any());
    }

    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        UserRegisterRequest user = new UserRegisterRequest("johndoe", "johndoe@example.com", "john@12345");

        Mockito.when(userRepo.findByEmail(user.email()))
                .thenReturn(new UserEntity());

        Assertions.assertThrows(
                EmailAlreadyInUseException.class,
                () -> useCase.execute(user)
        );

        Mockito.verify(userRepo, Mockito.never()).save(Mockito.any());
    }

}