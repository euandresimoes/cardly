package br.com.euandresimoes.user_service.infrastructure.services;

import br.com.euandresimoes.user_service.application.dtos.UserProfileResponse;
import br.com.euandresimoes.user_service.application.exceptions.UserNotFoundException;
import br.com.euandresimoes.user_service.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService implements IUserProfileService {

    private final UserRepository userRepo;
    private final TokenService tokenService;

    public UserProfileService(UserRepository userRepo, TokenService tokenService) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
    }

    @Override
    public UserProfileResponse execute(String authHeader) {
        var token = tokenService.extract(authHeader);
        var decoded = tokenService.decode(token);

        var user = userRepo.findByUsername(decoded.getSubject());

        if (user == null) {
            throw new UserNotFoundException("User not found.");
        }

        return new UserProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getDisplay_name(),
                user.getEmail(),
                user.getRole()
        );
    }
}
