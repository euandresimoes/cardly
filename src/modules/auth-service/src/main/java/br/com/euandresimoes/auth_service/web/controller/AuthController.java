package br.com.euandresimoes.auth_service.web.controllers;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserLoginRequest;
import br.com.euandresimoes.auth_service.application.dtos.requests.UserRegisterRequest;
import br.com.euandresimoes.auth_service.application.useCases.login.UserLoginImpl;
import br.com.euandresimoes.auth_service.application.useCases.register.UserRegisterUseCase;
import br.com.euandresimoes.auth_service.domain.repositories.UserRepository;
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
    private final UserLoginImpl loginUseCase;

    public AuthController(UserRepository userRepo, UserRegisterUseCase registerUseCase, UserLoginImpl loginUseCase) {
        this.userRepo = userRepo;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody @Valid UserLoginRequest data
    ) {
        try {
            var response = loginUseCase.execute(data);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
