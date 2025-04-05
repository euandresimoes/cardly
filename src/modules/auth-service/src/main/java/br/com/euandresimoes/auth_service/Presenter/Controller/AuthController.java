package br.com.euandresimoes.auth_service.Presenter.Controller;

import br.com.euandresimoes.auth_service.Application.DTOs.Request.UserRegisterRequest;
import br.com.euandresimoes.auth_service.Application.UseCases.UserRegisterUseCase;
import br.com.euandresimoes.auth_service.Domain.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final UserRepository userRepo;
    private final UserRegisterUseCase registerUseCase;

    public AuthController(UserRepository userRepo, UserRegisterUseCase registerUseCase) {
        this.userRepo = userRepo;
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody @Valid UserRegisterRequest data
    ) {
        try {
            registerUseCase.execute(data);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
