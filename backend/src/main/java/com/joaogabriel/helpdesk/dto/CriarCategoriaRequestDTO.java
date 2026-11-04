package com.joaogabriel.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarCategoriaRequestDTO(

        @NotBlank
        @Size(max = 150)
        String nome,

        @Size(max = 255)
        String descricao

) {
}
