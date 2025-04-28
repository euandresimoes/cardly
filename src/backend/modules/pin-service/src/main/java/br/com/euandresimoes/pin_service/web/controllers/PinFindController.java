package br.com.euandresimoes.pin_service.web.controllers;

import br.com.euandresimoes.pin_service.application.dtos.requests.PinFindByIdRequest;
import br.com.euandresimoes.pin_service.application.useCases.find.PinFindImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/find")
public class PinFindController {

    private final PinFindImpl pinFind;

    public PinFindController(PinFindImpl pinFind) {
        this.pinFind = pinFind;
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(
            @RequestHeader(name = "Authorization") String authHeader
    ) {
        try {
            return ResponseEntity.ok().body(pinFind.findAll(authHeader));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/id")
    public ResponseEntity<?> findById(
            @RequestBody @Valid PinFindByIdRequest data
    ) {
        try {
            return ResponseEntity.ok().body(pinFind.findById(data));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
