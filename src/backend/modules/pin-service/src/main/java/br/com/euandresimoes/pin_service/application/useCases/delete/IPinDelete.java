package br.com.euandresimoes.pin_service.application.useCases.delete;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinDeleteRequest;

public interface IPinDelete {
    void execute(String authHeader, PinDeleteRequest data);
}
