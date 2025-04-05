package br.com.euandresimoes.auth_service.Application.UseCases;

import br.com.euandresimoes.auth_service.Application.DTOs.Request.UserRegisterRequest;
import br.com.euandresimoes.auth_service.Application.Exceptions.EmailAlreadyInUseException;
import br.com.euandresimoes.auth_service.Application.Exceptions.UsernameAlreadyInUseException;
import br.com.euandresimoes.auth_service.Domain.Entity.UserEntity;
import br.com.euandresimoes.auth_service.Domain.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserRegisterUseCaseTest {

    private UserRepository userRepo;
    private UserRegisterUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepo = Mockito.mock(UserRepository.class);
        useCase = new UserRegisterUseCase(userRepo);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        UserRegisterRequest request = new UserRegisterRequest("johndoe", "johndoe@example.com", "john@12345");

        Mockito.when(userRepo.findByUsername(request.username())).thenReturn(null);
        Mockito.when(userRepo.findByEmail(request.email())).thenReturn(null);

        useCase.execute(request);

        Mockito.verify(userRepo).save(Mockito.any());
    }

    @Test
    void shouldThrowWhenUsernameAlreadyExists() {
        UserRegisterRequest user = new UserRegisterRequest("johndoe", "johndoe@example.com", "john@12345");

        Mockito.when(userRepo.findByUsername(user.username()))
                        .thenReturn(new UserEntity());

        Assertions.assertThrows(
                UsernameAlreadyInUseException.class,
                () -> useCase.execute(user)
        );

        Mockito.verify(userRepo, Mockito.never()).save(Mockito.any());
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