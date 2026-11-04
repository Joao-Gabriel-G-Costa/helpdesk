
package com.joaogabriel.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CriarChamadoRequestDTO(

        @NotBlank
        @Size(max = 200)
        String titulo,

        @NotBlank
        String descricao,

        @NotNull
        Long categoriaId

) {
}
