package br.com.euandresimoes.pin_service.application.useCases.delete;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinDeleteRequest;
import br.com.euandresimoes.pin_service.application.exceptions.InvalidUserIdException;
import br.com.euandresimoes.pin_service.application.exceptions.PinNotFoundException;
import br.com.euandresimoes.pin_service.domain.repository.PinRepository;
import br.com.euandresimoes.pin_service.infrastructure.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PinDeleteImpl implements IPinDelete {

    private final PinRepository pinRepo;
    private final TokenService tokenService;

    public PinDeleteImpl(PinRepository pinRepo, TokenService tokenService) {
        this.pinRepo = pinRepo;
        this.tokenService = tokenService;
    }

    @Override
    public void execute(String authHeader, @Valid PinDeleteRequest data) {
        try {
            String token = tokenService.extract(authHeader);
            var decoded = tokenService.decode(token);

            var pinExists = pinRepo.findById(data.id())
                    .orElseThrow(() -> new PinNotFoundException("Pin not found."));

            if (!Objects.equals(pinExists.getPinCreatorId(), decoded.getClaim("id").asLong())) {
                throw new InvalidUserIdException("Invalid user ID");
            }

            pinRepo.delete(pinExists);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
