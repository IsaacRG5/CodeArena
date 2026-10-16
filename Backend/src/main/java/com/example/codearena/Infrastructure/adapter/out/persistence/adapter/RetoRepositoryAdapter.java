package com.example.codearena.Infrastructure.adapter.out.persistence.adapter;

import com.example.codearena.Domain.model.*;
import com.example.codearena.Domain.model.utils.PaginaSolicitud;
import com.example.codearena.Domain.model.utils.ResultadoPaginado;
import com.example.codearena.Domain.port.out.RetoRepositoryPort;
import com.example.codearena.Infrastructure.adapter.out.persistence.repository.RetoJpaRepository;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.RetoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class RetoRepositoryAdapter implements RetoRepositoryPort {

    private final RetoJpaRepository retoJpaRepository;


    public RetoRepositoryAdapter (RetoJpaRepository retoJpaRepository){
        this.retoJpaRepository = retoJpaRepository;
    }

    private RetoEntity toEntity(Reto reto) {
        CategoriaEntity categoriaEntity = CategoriaEntity.builder()
                .id(reto.getCategoria().getId())
                .nombre(reto.getCategoria().getNombre())
                .build();

        return RetoEntity.builder()
                .id(reto.getId())
                .titulo(reto.getTitulo())
                .descripcion(reto.getDescripcion())
                .categoria(categoriaEntity)
                .dificultad(reto.getDificultad())
                .fechaCreacion(reto.getFechaCreacion())
                .fechaLimite(reto.getFechaLimite())
                .estado(reto.getEstado())
                .build();
    }

    private Reto toDomain(RetoEntity entity) {
        Categoria categoria = new Categoria(
                entity.getCategoria().getId(),
                entity.getCategoria().getNombre()
        );

        return new Reto(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescripcion(),
                categoria,
                entity.getDificultad(),
                entity.getFechaCreacion(),
                entity.getFechaLimite(),
                entity.getEstado()
        );
    }

    @Override
    public Reto guardar (Reto reto){
        RetoEntity retoEntity = toEntity(reto);
        RetoEntity guardar = retoJpaRepository.save(retoEntity);
        return toDomain(guardar);
    }

    @Override
    public Optional<Reto> buscarPorId(Long id){
        return retoJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public ResultadoPaginado<Reto> buscarTodos(PaginaSolicitud solicitud) {
        Pageable pageable = PageRequest.of(solicitud.pagina(), solicitud.tamano());
        Page<RetoEntity> pagina = retoJpaRepository.findAll(pageable);

        List<Reto> reto = pagina.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new ResultadoPaginado<>(
                reto,
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }

    @Override
    public ResultadoPaginado<Reto> buscarTodosActivos(PaginaSolicitud solicitud) {
        Pageable pageable = PageRequest.of(solicitud.pagina(), solicitud.tamano());
        Page<RetoEntity> pagina = retoJpaRepository.findByEstado(EstadoReto.ACTIVO, pageable);

        List<Reto> reto = pagina.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new ResultadoPaginado<>(
                reto,
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }

    @Override
    public ResultadoPaginado<Reto>  buscarPorDificultad(Dificultad dificultad, PaginaSolicitud solicitud) {
        Pageable pageable = PageRequest.of(solicitud.pagina(), solicitud.tamano());
        Page<RetoEntity> pagina = retoJpaRepository.findByDificultad(dificultad,  pageable);

        List<Reto> reto = pagina.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new ResultadoPaginado<>(
                reto,
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }

    @Override
    public ResultadoPaginado<Reto>  buscarPorCategoria (Long categoriaId, PaginaSolicitud solicitud) {
        Pageable pageable = PageRequest.of(solicitud.pagina(), solicitud.tamano());
        Page<RetoEntity> pagina = retoJpaRepository.findByCategoriaId(categoriaId, pageable);

        List<Reto> reto = pagina.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new ResultadoPaginado<>(
                reto,
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }

    @Override
    public ResultadoPaginado<Reto>  buscarProximosAVencer (LocalDateTime antesDe, PaginaSolicitud solicitud) {
        Pageable pageable = PageRequest.of(solicitud.pagina(), solicitud.tamano());
        Page<RetoEntity> pagina = retoJpaRepository.findByFechaLimiteBefore(antesDe, pageable);

        List<Reto> reto = pagina.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new ResultadoPaginado<>(
                reto,
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }
}
