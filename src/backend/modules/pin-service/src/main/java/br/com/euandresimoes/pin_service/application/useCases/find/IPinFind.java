package br.com.euandresimoes.pin_service.application.useCases.find;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinFindByIdRequest;
import br.com.euandresimoes.pin_service.domain.entity.PinEntity;

import java.util.List;

public interface IPinFind {
    List<PinEntity> findAll(String authHeader);
    PinEntity findById(PinFindByIdRequest data);
}
