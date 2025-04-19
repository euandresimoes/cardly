package br.com.euandresimoes.pin_service.application.useCases.create;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinCreateRequest;

public interface IPinCreate {
    void execute(PinCreateRequest data, String authHeader);
}
