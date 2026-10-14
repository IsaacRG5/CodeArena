package com.example.codearena.Domain.port.out;

import com.example.codearena.Domain.model.Participacion;
import com.example.codearena.Domain.model.utils.PaginaSolicitud;
import com.example.codearena.Domain.model.utils.ResultadoPaginado;

import java.util.Optional;

public interface ParticipacionRepositoryPort {

    Participacion guardar(Participacion participacion);

    Optional<Participacion> buscarPorId(Long id);

    boolean existeParticipacionActiva(Long usuarioId, Long retoId);

    Optional<Participacion> buscarParticipancioActiva(Long usuarioId, Long retoId);

    ResultadoPaginado<Participacion> buscarPorUsuario(Long usuarioId, PaginaSolicitud solicitud);

    ResultadoPaginado<Participacion> buscarActivaPorUsuario(Long usuarioId, PaginaSolicitud solicitud);

    ResultadoPaginado<Participacion> buscarCompletadasPorUsuario(Long usuarioId, PaginaSolicitud solicitud);

    Long contarCompletadasPorUsuario(Long usuarioId);
}
