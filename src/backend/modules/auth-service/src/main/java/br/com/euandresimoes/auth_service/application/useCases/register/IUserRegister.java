package br.com.euandresimoes.auth_service.application.useCases.register;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserRegisterRequest;
import jakarta.validation.Valid;

public interface IUserRegister {
    void execute(@Valid UserRegisterRequest data);
}
