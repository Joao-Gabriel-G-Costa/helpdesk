package com.joaogabriel.helpdesk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequestDTO(

        @NotBlank
        @Size(max = 150)
        String nome,

        @NotBlank
        @Email
        @Size(max = 150)
        String email,

        @NotBlank
        @Size(min = 8)
        String senha,

        @NotBlank
        String perfil

) {
}
