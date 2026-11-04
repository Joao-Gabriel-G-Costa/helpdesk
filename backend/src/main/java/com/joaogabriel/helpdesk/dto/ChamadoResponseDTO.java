
package com.joaogabriel.helpdesk.dto;

import java.time.OffsetDateTime;

public record ChamadoResponseDTO(

        Long id,
        String titulo,
        String descricao,
        String status,
        String prioridade,
        Long solicitanteId,
        Long tecnicoId,
        Long categoriaId,
        OffsetDateTime criadoEm,
        OffsetDateTime atualizadoEm

) {
}
