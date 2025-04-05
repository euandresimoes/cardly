package br.com.euandresimoes.auth_service.Application.UseCases;

import br.com.euandresimoes.auth_service.Application.Exceptions.EmailAlreadyInUseException;
import br.com.euandresimoes.auth_service.Application.Exceptions.UsernameAlreadyInUseException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import br.com.euandresimoes.auth_service.Application.DTOs.Request.UserRegisterRequest;
import br.com.euandresimoes.auth_service.Domain.Repository.UserRepository;

@Service
public class UserRegisterUseCase {

    private final UserRepository userRepo;

    public UserRegisterUseCase(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void execute(@Valid UserRegisterRequest data) {
        if (userRepo.findByUsername(data.username()) != null) {
            throw new UsernameAlreadyInUseException("This username is already in use.");
        }

        if (userRepo.findByEmail(data.email()) != null) {
            throw new EmailAlreadyInUseException("This email is already in use.");
        }

        userRepo.save(data.toEntity());
    }
}
