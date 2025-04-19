package br.com.euandresimoes.pin_service.web.controllers;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinCreateRequest;
import br.com.euandresimoes.pin_service.application.useCases.create.PinCreateImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PinCreateController {

    private final PinCreateImpl pinCreate;

    public PinCreateController(PinCreateImpl pinCreate) {
        this.pinCreate = pinCreate;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestHeader(name = "Authorization") String authHeader,
            @RequestBody @Valid PinCreateRequest data
            ) {
        try {
            pinCreate.execute(data, authHeader);
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
