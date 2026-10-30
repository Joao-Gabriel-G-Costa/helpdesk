package com.joaogabriel.helpdesk.dto;

import java.time.OffsetDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String perfil,
        Boolean ativo,
        OffsetDateTime criadoEm
) {
}
