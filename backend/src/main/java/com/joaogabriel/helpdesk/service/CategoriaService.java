package com.joaogabriel.helpdesk.service;

import com.joaogabriel.helpdesk.dto.CategoriaResponseDTO;
import com.joaogabriel.helpdesk.dto.CriarCategoriaRequestDTO;
import com.joaogabriel.helpdesk.model.Categoria;
import com.joaogabriel.helpdesk.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponseDTO> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public CategoriaResponseDTO cadastrar(CriarCategoriaRequestDTO dados) {

        String nome = dados.nome().trim();

        if (categoriaRepository.existsByNomeIgnoreCase(nome)) {
            throw new IllegalArgumentException("Categoria já cadastrada.");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setDescricao(
                dados.descricao() == null ? null : dados.descricao().trim()
        );
        categoria.setAtiva(true);

        Categoria categoriaSalva = categoriaRepository.saveAndFlush(categoria);

        return converterParaDTO(categoriaSalva);
    }

    private CategoriaResponseDTO converterParaDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getAtiva()
        );
    }
}
