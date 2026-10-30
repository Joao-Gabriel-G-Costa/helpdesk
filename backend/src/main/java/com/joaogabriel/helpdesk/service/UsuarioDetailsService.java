package com.joaogabriel.helpdesk.service;

import com.joaogabriel.helpdesk.model.Usuario;
import com.joaogabriel.helpdesk.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository
                .findByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Usuário não encontrado."
                        )
                );

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenhaHash())
                .roles(usuario.getPerfil())
                .disabled(!Boolean.TRUE.equals(usuario.getAtivo()))
                .build();
    }
}
