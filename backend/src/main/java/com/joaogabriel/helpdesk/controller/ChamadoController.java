
package com.joaogabriel.helpdesk.controller;

import com.joaogabriel.helpdesk.dto.ChamadoResponseDTO;
import com.joaogabriel.helpdesk.dto.CriarChamadoRequestDTO;
import com.joaogabriel.helpdesk.service.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> cadastrar(
            @Valid @RequestBody CriarChamadoRequestDTO dados,
            Authentication authentication) {

        ChamadoResponseDTO chamado =
                chamadoService.cadastrar(dados, authentication);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(chamado);
    }
}
