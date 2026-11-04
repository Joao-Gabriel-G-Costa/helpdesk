package com.joaogabriel.helpdesk.repository;

import com.joaogabriel.helpdesk.model.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    List<Chamado> findBySolicitanteId(Long solicitanteId);

    List<Chamado> findByTecnicoId(Long tecnicoId);

    List<Chamado> findByStatus(String status);

    List<Chamado> findByCategoriaId(Long categoriaId);
}
