
package com.joaogabriel.helpdesk.service;

import com.joaogabriel.helpdesk.dto.CriarUsuarioRequestDTO;
import com.joaogabriel.helpdesk.dto.UsuarioResponseDTO;
import com.joaogabriel.helpdesk.model.Usuario;
import com.joaogabriel.helpdesk.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public UsuarioResponseDTO cadastrar(CriarUsuarioRequestDTO dados) {

        String email = dados.email().trim().toLowerCase();
        String perfil = dados.perfil().trim().toUpperCase();

        if (usuarioRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (!Set.of("ADMIN", "TECNICO", "SOLICITANTE").contains(perfil)) {
            throw new IllegalArgumentException("Perfil inválido.");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(dados.nome().trim());
        usuario.setEmail(email);
        usuario.setSenhaHash(passwordEncoder.encode(dados.senha()));
        usuario.setPerfil(perfil);
        usuario.setAtivo(true);

        Usuario usuarioSalvo = usuarioRepository.saveAndFlush(usuario);

        return usuarioRepository.findById(usuarioSalvo.getId())
                .map(this::converterParaDTO)
                .orElseThrow(() -> new IllegalStateException(
                        "Não foi possível consultar o usuário cadastrado."
                ));
    }

    private UsuarioResponseDTO converterParaDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPerfil(),
                usuario.getAtivo(),
                usuario.getCriadoEm()
        );
    }
}
