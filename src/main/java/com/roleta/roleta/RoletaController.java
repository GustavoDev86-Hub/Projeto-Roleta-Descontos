package com.roleta.roleta;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roleta")
@CrossOrigin(origins = "*") // Permite chamadas de qualquer front-end
public class RoletaController {

    private final RoletaService roletaService;

    public RoletaController(RoletaService roletaService) {
        this.roletaService = roletaService;
    }

    // A tela dispara um POST para esta URL ao clicar em "Girar agora"
    @PostMapping("/girar")
    public ResponseEntity<Integer> girar(@RequestBody RoletaRequestDTO dto) {
        int premio = roletaService.processarGiro(dto);
        return ResponseEntity.ok(premio);
    }
}
