package br.com.euandresimoes.pin_service.web.controllers;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinDeleteRequest;
import br.com.euandresimoes.pin_service.application.useCases.delete.PinDeleteImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PinDeleteController {

    private final PinDeleteImpl pinDelete;

    public PinDeleteController(PinDeleteImpl pinDelete) {
        this.pinDelete = pinDelete;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader(name = "Authorization") String authHeader,
            @RequestBody @Valid PinDeleteRequest data
    ) {
        try {
            pinDelete.execute(authHeader, data);

            return ResponseEntity
                    .noContent()
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
