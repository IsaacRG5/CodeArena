package com.example.codearena.Infrastructure.adapter.out.persistence.adapter;

import com.example.codearena.Domain.model.*;
import com.example.codearena.Domain.model.utils.PaginaSolicitud;
import com.example.codearena.Domain.model.utils.ResultadoPaginado;
import com.example.codearena.Domain.port.out.ParticipacionRepositoryPort;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.ParticipacionEntity;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.RetoEntity;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.example.codearena.Infrastructure.adapter.out.persistence.repository.ParticipacionJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ParticipacionRepositoryAdapter implements ParticipacionRepositoryPort {

    private static final List<EstadoParticipacion> ESTADOS_ACTIVOS =
            List.of(EstadoParticipacion.ACCEPTED, EstadoParticipacion.IN_PROGRESS);

    private final ParticipacionJpaRepository participacionJpaRepository;

    public ParticipacionRepositoryAdapter(ParticipacionJpaRepository participacionJpaRepository) {
        this.participacionJpaRepository = participacionJpaRepository;
    }

    private ParticipacionEntity toEntity(Participacion participacion) {
        UsuarioEntity usuarioRef = UsuarioEntity.builder()
                .id(participacion.getUsuario().getId())
                .build();

        RetoEntity retoRef = RetoEntity.builder()
                .id(participacion.getReto().getId())
                .build();

        return ParticipacionEntity.builder()
                .id(participacion.getId())
                .usuario(usuarioRef)
                .reto(retoRef)
                .fechaInicio(participacion.getFechaInicio())
                .fechaEntrega(participacion.getFechaEntrega())
                .estado(participacion.getEstado())
                .solucionEnviada(participacion.getSolucionEnviada())
                .experienciaObtenida(participacion.getExperienciaObtenida())
                .build();
    }

    private Participacion toDomain(ParticipacionEntity entity) {
        UsuarioEntity ue = entity.getUsuario();
        Usuario usuario = new Usuario(
                ue.getId(), ue.getNombre(), ue.getUsername(), ue.getEmail(),
                ue.getPassword(), ue.getRol(), ue.getNivel(),
                ue.getExperienciaAcumulada(), ue.getEstado()
        );

        RetoEntity re = entity.getReto();
        Categoria categoria = new Categoria(re.getCategoria().getId(), re.getCategoria().getNombre());
        Reto reto = new Reto(
                re.getId(), re.getTitulo(), re.getDescripcion(), categoria,
                re.getDificultad(), re.getFechaCreacion(), re.getFechaLimite(), re.getEstado()
        );

        return new Participacion(
                entity.getId(), usuario, reto, entity.getFechaInicio(),
                entity.getFechaEntrega(), entity.getEstado(),
                entity.getSolucionEnviada(), entity.getExperienciaObtenida()
        );
    }

    private Pageable toPageable(PaginaSolicitud solicitud) {
        return PageRequest.of(solicitud.pagina(), solicitud.tamano());
    }

    private ResultadoPaginado<Participacion> toResultadoPaginado(Page<ParticipacionEntity> pagina) {
        List<Participacion> participaciones = pagina.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new ResultadoPaginado<>(
                participaciones,
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }

    @Override
    public Participacion guardar(Participacion participacion) {
        ParticipacionEntity entity = toEntity(participacion);
        ParticipacionEntity guardado = participacionJpaRepository.save(entity);
        return toDomain(guardado);
    }

    @Override
    public Optional<Participacion> buscarPorId(Long id) {
        return participacionJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public boolean existeParticipacionActiva(Long usuarioId, Long retoId) {
        return participacionJpaRepository.existsByUsuarioIdAndRetoIdAndEstadoIn(usuarioId, retoId, ESTADOS_ACTIVOS);
    }

    @Override
    public Optional<Participacion> buscarParticipancioActiva(Long usuarioId, Long retoId) {
        return participacionJpaRepository.findFirstByUsuarioIdAndRetoIdAndEstadoIn(usuarioId, retoId, ESTADOS_ACTIVOS)
                .map(this::toDomain);
    }

    @Override
    public ResultadoPaginado<Participacion> buscarPorUsuario(Long usuarioId, PaginaSolicitud solicitud) {
        Page<ParticipacionEntity> pagina = participacionJpaRepository.findByUsuarioId(usuarioId, toPageable(solicitud));
        return toResultadoPaginado(pagina);
    }

    @Override
    public ResultadoPaginado<Participacion> buscarActivaPorUsuario(Long usuarioId, PaginaSolicitud solicitud) {
        Page<ParticipacionEntity> pagina = participacionJpaRepository
                .findByUsuarioIdAndEstadoIn(usuarioId, ESTADOS_ACTIVOS, toPageable(solicitud));
        return toResultadoPaginado(pagina);
    }

    @Override
    public ResultadoPaginado<Participacion> buscarCompletadasPorUsuario(Long usuarioId, PaginaSolicitud solicitud) {
        Page<ParticipacionEntity> pagina = participacionJpaRepository
                .findByUsuarioIdAndEstado(usuarioId, EstadoParticipacion.APPROVED, toPageable(solicitud));
        return toResultadoPaginado(pagina);
    }

    @Override
    public Long contarCompletadasPorUsuario(Long usuarioId) {
        return participacionJpaRepository.countByUsuarioIdAndEstado(usuarioId, EstadoParticipacion.APPROVED);
    }
}