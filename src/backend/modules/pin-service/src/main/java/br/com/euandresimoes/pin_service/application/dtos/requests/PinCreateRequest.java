package br.com.euandresimoes.pin_service.application.dtos.requests;

import br.com.euandresimoes.pin_service.domain.entity.enums.PinType;
import br.com.euandresimoes.pin_service.domain.entity.models.StyleModel;
import jakarta.validation.constraints.NotNull;

public record PinCreateRequest(
        @NotNull
        PinType type,
        @NotNull
        String title,
        @NotNull
        String link,
        @NotNull
        boolean isPublic,
        @NotNull
        CardStyle style
) {

        public StyleModel toStyleModel() {
                return new StyleModel(
                        style.pageBackground(),
                        style.cardBackground(),
                        style.cardBorderColor(),
                        style.cardTextColor(),
                        style.cardButtonColor(),
                        style.cardButtonTextColor(),
                        style.cardFontFamily()
                );
        }

}

