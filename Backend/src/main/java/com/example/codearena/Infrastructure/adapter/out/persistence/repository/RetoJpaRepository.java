package com.example.codearena.Infrastructure.adapter.out.persistence.repository;

import com.example.codearena.Domain.model.Dificultad;
import com.example.codearena.Domain.model.EstadoReto;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.RetoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface RetoJpaRepository extends JpaRepository<RetoEntity, Long> {

    Page<RetoEntity> findByEstado(EstadoReto estado, Pageable pageable);

    Page<RetoEntity> findByDificultad(Dificultad dificultad, Pageable pageable);

    Page<RetoEntity> findByCategoriaId(Long categoriaId, Pageable pageable);

    Page<RetoEntity> findByFechaLimiteBefore(LocalDateTime antesDe, Pageable pageable);
}
