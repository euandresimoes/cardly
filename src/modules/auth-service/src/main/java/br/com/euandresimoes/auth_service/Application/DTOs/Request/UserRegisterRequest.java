package br.com.euandresimoes.auth_service.Application.DTOs.Request;

import br.com.euandresimoes.auth_service.Domain.Entity.UserEntity;
import br.com.euandresimoes.auth_service.Domain.Enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(
    @NotNull
    String username,
    @NotNull
    String email,
    @NotNull
    String password
) {
    public UserEntity toEntity() {
        return new UserEntity(username, email, password, UserRole.USER);
    }
}
