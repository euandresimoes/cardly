package br.com.euandresimoes.user_service.infrastructure.services;

import br.com.euandresimoes.user_service.application.dtos.UserProfileResponse;

public interface IUserProfileService {
    UserProfileResponse execute(String authHeader);
}
