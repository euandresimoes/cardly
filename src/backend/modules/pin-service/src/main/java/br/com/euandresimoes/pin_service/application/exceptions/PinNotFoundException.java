package br.com.euandresimoes.pin_service.application.exceptions;

public class PinNotFoundException extends RuntimeException {
    public PinNotFoundException(String message) {
        super(message);
    }
}
