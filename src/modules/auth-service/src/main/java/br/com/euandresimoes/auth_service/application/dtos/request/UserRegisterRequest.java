package br.com.euandresimoes.auth_service.application.dtos.request;

import br.com.euandresimoes.auth_service.domain.entity.UserEntity;
import br.com.euandresimoes.auth_service.domain.enums.UserRole;
import br.com.euandresimoes.auth_service.shared.utils.DataGenerator;
import br.com.euandresimoes.auth_service.shared.utils.PasswordHasher;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(
        @NotNull
        String displayName,
        @NotNull
        String email,
        @NotNull
        String password
) { }
