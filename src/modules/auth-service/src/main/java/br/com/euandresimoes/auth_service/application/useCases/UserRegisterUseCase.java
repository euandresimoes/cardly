package br.com.euandresimoes.auth_service.application.useCases;

import br.com.euandresimoes.auth_service.application.exceptions.EmailAlreadyInUseException;
import br.com.euandresimoes.auth_service.domain.entity.UserEntity;
import br.com.euandresimoes.auth_service.domain.enums.UserRole;
import br.com.euandresimoes.auth_service.shared.utils.DataGenerator;
import br.com.euandresimoes.auth_service.infrastructure.services.PasswordHashService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import br.com.euandresimoes.auth_service.application.dtos.request.UserRegisterRequest;
import br.com.euandresimoes.auth_service.domain.repository.UserRepository;

@Service
public class UserRegisterUseCase {

    private final UserRepository userRepo;
    private final PasswordHashService passwordHash;

    public UserRegisterUseCase(UserRepository userRepo, PasswordHashService passwordHash) {
        this.userRepo = userRepo;
        this.passwordHash = passwordHash;
    }

    public void execute(@Valid UserRegisterRequest data) {
        UserEntity newUser = new UserEntity(
                DataGenerator.genUsername(),
                data.displayName(),
                data.email(),
                passwordHash.encode(data.password()),
                UserRole.USER
        );

        if (userRepo.findByEmail(newUser.getEmail()) != null) {
            throw new EmailAlreadyInUseException("This email is already in use.");
        }

        userRepo.save(newUser);
    }
}
