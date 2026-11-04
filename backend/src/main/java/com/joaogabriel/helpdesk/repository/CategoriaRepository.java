package com.joaogabriel.helpdesk.repository;

import com.joaogabriel.helpdesk.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNomeIgnoreCase(String nome);

}
