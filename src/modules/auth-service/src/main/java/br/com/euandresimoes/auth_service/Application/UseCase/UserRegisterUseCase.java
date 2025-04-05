package br.com.euandresimoes.auth_service.Application.UseCase;

import org.springframework.stereotype.Service;

import br.com.euandresimoes.auth_service.Application.DTOs.Request.UserRegisterRequest;
import br.com.euandresimoes.auth_service.Domain.Repository.UserRepository;

@Service
public class UserRegisterUseCase {

    private final UserRepository userRepo;

    public UserRegisterUseCase(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void execute(UserRegisterRequest data) {
        if (userRepo.findByUsername(data.username()) != null) {

        }
    }
}
