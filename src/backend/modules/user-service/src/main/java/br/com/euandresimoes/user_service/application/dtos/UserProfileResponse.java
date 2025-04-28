package br.com.euandresimoes.user_service.application.dtos;

import br.com.euandresimoes.user_service.domain.entity.enums.UserRole;

public record UserProfileResponse(
        Long id,
        String username,
        String display_name,
        String email,
        UserRole role
) {
}
