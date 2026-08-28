package com.roleta.roleta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roleta")
public class RoletaController {

    private final RoletaService roletaService;

    // Injeção da Service via Construtor
    public RoletaController(RoletaService roletaService) {
        this.roletaService = roletaService;
    }

    @GetMapping("/girar")
    public ResponseEntity<Integer> girar() {
        int valorSorteado = roletaService.girarRoleta();
        return ResponseEntity.ok(valorSorteado);
    }
}
