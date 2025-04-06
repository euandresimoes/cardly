package br.com.euandresimoes.auth_service.application.useCases;

import br.com.euandresimoes.auth_service.application.dtos.request.UserRegisterRequest;
import br.com.euandresimoes.auth_service.application.exceptions.EmailAlreadyInUseException;
import br.com.euandresimoes.auth_service.application.exceptions.UsernameAlreadyInUseException;
import br.com.euandresimoes.auth_service.domain.entity.UserEntity;
import br.com.euandresimoes.auth_service.domain.repository.UserRepository;
import br.com.euandresimoes.auth_service.shared.utils.PasswordHasher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserRegisterUseCaseTest {

    private UserRepository userRepo;
    private PasswordHasher passwordHasher;
    private UserRegisterUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepository.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        passwordHasher = new PasswordHasher(passwordEncoder);
        useCase = new UserRegisterUseCase(userRepo, passwordHasher);
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