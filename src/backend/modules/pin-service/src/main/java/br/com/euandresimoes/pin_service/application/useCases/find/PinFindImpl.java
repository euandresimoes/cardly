package br.com.euandresimoes.pin_service.application.useCases.find;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinFindByIdRequest;
import br.com.euandresimoes.pin_service.application.exceptions.PinNotFoundException;
import br.com.euandresimoes.pin_service.domain.entity.PinEntity;
import br.com.euandresimoes.pin_service.domain.repository.PinRepository;
import br.com.euandresimoes.pin_service.infrastructure.services.TokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinFindImpl implements IPinFind {

    private final PinRepository pinRepo;
    private final TokenService tokenService;

    public PinFindImpl(PinRepository pinRepo, TokenService tokenService) {
        this.pinRepo = pinRepo;
        this.tokenService = tokenService;
    }

    @Override
    public List<PinEntity> findAll(String authHeader) {
        var token = tokenService.extract(authHeader);
        var decoded = tokenService.decode(token);

        return pinRepo.findAllByPinCreatorUsername(decoded.getSubject());
    }

    @Override
    public PinEntity findById(PinFindByIdRequest data) {
        return pinRepo.findById(data.id())
                .orElseThrow(() -> new PinNotFoundException("Pin not found."));
    }
}
