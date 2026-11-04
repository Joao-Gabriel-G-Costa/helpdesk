
package com.joaogabriel.helpdesk.service;

import com.joaogabriel.helpdesk.dto.ChamadoResponseDTO;
import com.joaogabriel.helpdesk.dto.CriarChamadoRequestDTO;
import com.joaogabriel.helpdesk.model.Categoria;
import com.joaogabriel.helpdesk.model.Chamado;
import com.joaogabriel.helpdesk.model.Usuario;
import com.joaogabriel.helpdesk.repository.CategoriaRepository;
import com.joaogabriel.helpdesk.repository.ChamadoRepository;
import com.joaogabriel.helpdesk.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

@Service
public class ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final EntityManager entityManager;


    public ChamadoService(
            ChamadoRepository chamadoRepository,
            UsuarioRepository usuarioRepository,
            CategoriaRepository categoriaRepository,
            EntityManager entityManager) {

        this.chamadoRepository = chamadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.entityManager = entityManager;
    }


    @Transactional
    public ChamadoResponseDTO cadastrar(
            CriarChamadoRequestDTO dados,
            Authentication authentication) {

        Usuario solicitante = usuarioRepository
                .findByEmailIgnoreCase(authentication.getName())
                .orElseThrow(() ->
                        new IllegalArgumentException("Usuário não encontrado.")
                );

        Categoria categoria = categoriaRepository
                .findById(dados.categoriaId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Categoria não encontrada.")
                );

        if (!Boolean.TRUE.equals(categoria.getAtiva())) {
            throw new IllegalArgumentException("Categoria inativa.");
        }

        Chamado chamado = new Chamado();
        chamado.setTitulo(dados.titulo().trim());
        chamado.setDescricao(dados.descricao().trim());
        chamado.setStatus("ABERTO");
        chamado.setPrioridade("MEDIA");
        chamado.setSolicitante(solicitante);
        chamado.setCategoria(categoria);

        Chamado chamadoSalvo = chamadoRepository.saveAndFlush(chamado);

        entityManager.refresh(chamadoSalvo);

        return converterParaDTO(chamadoSalvo);
    }

    private ChamadoResponseDTO converterParaDTO(Chamado chamado) {

        return new ChamadoResponseDTO(
                chamado.getId(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getStatus(),
                chamado.getPrioridade(),
                chamado.getSolicitante().getId(),
                chamado.getTecnico() == null
                        ? null
                        : chamado.getTecnico().getId(),
                chamado.getCategoria().getId(),
                chamado.getCriadoEm(),
                chamado.getAtualizadoEm()
        );
    }
}
