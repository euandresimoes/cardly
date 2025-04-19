package br.com.euandresimoes.pin_service.application.dtos.requests;

import br.com.euandresimoes.pin_service.domain.entity.models.StyleModel;
import jakarta.validation.constraints.NotNull;

public record CardStyle(
        @NotNull
        String pageBackground,
        @NotNull
        String cardBackground,
        @NotNull
        String cardBorderColor,
        @NotNull
        String cardTextColor,
        @NotNull
        String cardButtonColor,
        @NotNull
        String cardButtonTextColor,
        @NotNull
        String cardFontFamily
) {
}
