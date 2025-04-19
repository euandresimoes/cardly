package br.com.euandresimoes.auth_service.application.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record UserRegisterRequest(
        @NotNull
        String displayName,
        @NotNull
        String email,
        @NotNull
        String password
) { }
