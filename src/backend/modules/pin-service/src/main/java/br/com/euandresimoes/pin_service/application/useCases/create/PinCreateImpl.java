package br.com.euandresimoes.pin_service.application.useCases.create;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinCreateRequest;
import br.com.euandresimoes.pin_service.domain.entity.PinEntity;
import br.com.euandresimoes.pin_service.domain.repository.PinRepository;
import br.com.euandresimoes.pin_service.infrastructure.services.TokenService;
import org.springframework.stereotype.Service;

@Service
public class PinCreateImpl implements IPinCreate{

    private final PinRepository pinRepository;
    private final TokenService tokenService;

    public PinCreateImpl(PinRepository pinRepository, TokenService tokenService) {
        this.pinRepository = pinRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void execute(PinCreateRequest data, String authHeader) {
        try {
            String token = tokenService.extract(authHeader);
            var decoded = tokenService.decode(token);

            PinEntity pin = new PinEntity(
                    data.type(),
                    data.title(),
                    data.link(),
                    data.isPublic(),
                    decoded.getClaim("id").asLong(),
                    decoded.getSubject(),
                    decoded.getClaim("displayName").asString(),
                    data.toStyleModel()
            );

            pinRepository.save(pin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
