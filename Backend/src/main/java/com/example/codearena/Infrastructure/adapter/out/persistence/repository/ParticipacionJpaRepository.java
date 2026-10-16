package com.example.codearena.Infrastructure.adapter.out.persistence.repository;

import com.example.codearena.Domain.model.EstadoParticipacion;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.ParticipacionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ParticipacionJpaRepository extends JpaRepository<ParticipacionEntity, Long> {

    boolean existsByUsuarioIdAndRetoIdAndEstadoIn(Long usuarioId, Long retoId, Collection<EstadoParticipacion> estados);

    Optional<ParticipacionEntity> findFirstByUsuarioIdAndRetoIdAndEstadoIn(Long usuarioId, Long retoId, Collection<EstadoParticipacion> estados);

    Page<ParticipacionEntity> findByUsuarioId(Long usuarioId, Pageable pageable);

    Page<ParticipacionEntity> findByUsuarioIdAndEstadoIn(Long usuarioId, Collection<EstadoParticipacion> estados, Pageable pageable);

    Page<ParticipacionEntity> findByUsuarioIdAndEstado(Long usuarioId, EstadoParticipacion estado, Pageable pageable);

    long countByUsuarioIdAndEstado(Long usuarioId, EstadoParticipacion estado);
}