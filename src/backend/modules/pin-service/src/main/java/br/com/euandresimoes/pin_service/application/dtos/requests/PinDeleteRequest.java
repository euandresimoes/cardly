package br.com.euandresimoes.pin_service.application.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record PinDeleteRequest(
        @NotNull
        Long id
) {
}
