package com.joaogabriel.helpdesk.controller;

import com.joaogabriel.helpdesk.dto.CriarUsuarioRequestDTO;
import com.joaogabriel.helpdesk.dto.UsuarioResponseDTO;
import com.joaogabriel.helpdesk.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @Valid @RequestBody CriarUsuarioRequestDTO dados
    ) {
        UsuarioResponseDTO usuario = usuarioService.cadastrar(dados);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }
}
