package br.com.euandresimoes.auth_service.application.useCases.login;

import br.com.euandresimoes.auth_service.application.dtos.requests.UserLoginRequest;
import br.com.euandresimoes.auth_service.application.dtos.responses.UserLoginResponse;
import jakarta.validation.Valid;

public interface IUserLogin {
    UserLoginResponse execute(@Valid UserLoginRequest data);
}
