
package com.joaogabriel.helpdesk.controller;

import com.joaogabriel.helpdesk.dto.CategoriaResponseDTO;
import com.joaogabriel.helpdesk.dto.CriarCategoriaRequestDTO;
import com.joaogabriel.helpdesk.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> cadastrar(
            @Valid @RequestBody CriarCategoriaRequestDTO dados) {

        CategoriaResponseDTO categoria = categoriaService.cadastrar(dados);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoria);
    }
}
