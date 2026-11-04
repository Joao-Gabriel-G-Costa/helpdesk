package com.joaogabriel.helpdesk.dto;

public record CategoriaResponseDTO(
        Long id,
        String nome,
        String descricao,
        Boolean ativa
) {
}
