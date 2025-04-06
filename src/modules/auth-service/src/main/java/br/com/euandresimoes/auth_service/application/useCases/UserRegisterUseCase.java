package br.com.euandresimoes.auth_service.application.useCases;

import br.com.euandresimoes.auth_service.application.exceptions.EmailAlreadyInUseException;
import br.com.euandresimoes.auth_service.application.exceptions.UsernameAlreadyInUseException;
import br.com.euandresimoes.auth_service.domain.entity.UserEntity;
import br.com.euandresimoes.auth_service.domain.enums.UserRole;
import br.com.euandresimoes.auth_service.shared.utils.DataGenerator;
import br.com.euandresimoes.auth_service.shared.utils.PasswordHasher;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import br.com.euandresimoes.auth_service.application.dtos.request.UserRegisterRequest;
import br.com.euandresimoes.auth_service.domain.repository.UserRepository;

@Service
public class UserRegisterUseCase {

    private final UserRepository userRepo;
    private final PasswordHasher passwordHasher;

    public UserRegisterUseCase(UserRepository userRepo, PasswordHasher passwordHasher) {
        this.userRepo = userRepo;
        this.passwordHasher = passwordHasher;
    }

    public void execute(@Valid UserRegisterRequest data) {
        UserEntity newUser = new UserEntity(
                DataGenerator.genUsername(),
                data.displayName(),
                data.email(),
                passwordHasher.encode(data.password()),
                UserRole.USER
        );

        if (userRepo.findByEmail(newUser.getEmail()) != null) {
            throw new EmailAlreadyInUseException("This email is already in use.");
        }

        userRepo.save(newUser);
    }
}
