package br.com.euandresimoes.pin_service.application.dtos.requests;

import jakarta.validation.constraints.NotNull;

public record PinFindByIdRequest(
        @NotNull
        Long id
) {
}
