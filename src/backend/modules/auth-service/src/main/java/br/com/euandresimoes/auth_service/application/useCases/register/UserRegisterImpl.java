package br.com.euandresimoes.auth_service.application.useCases.register;

import br.com.euandresimoes.auth_service.application.exceptions.EmailAlreadyInUseException;
import br.com.euandresimoes.auth_service.domain.entities.UserEntity;
import br.com.euandresimoes.auth_service.domain.entities.enums.UserRole;
import br.com.euandresimoes.auth_service.shared.utils.DataGenerator;
import br.com.euandresimoes.auth_service.infrastructure.services.passwordEncoder.PasswordHashImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserRegisterRequest;
import br.com.euandresimoes.auth_service.domain.repositories.UserRepository;

@Service
public class UserRegisterImpl implements IUserRegister {

    private final UserRepository userRepo;
    private final PasswordHashImpl passwordHash;

    public UserRegisterImpl(UserRepository userRepo, PasswordHashImpl passwordHash) {
        this.userRepo = userRepo;
        this.passwordHash = passwordHash;
    }

    @Override
    public void execute(UserRegisterRequest data) {
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
