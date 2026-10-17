package com.example.codearena.Application.Service;

import com.example.codearena.Domain.exception.*;
import com.example.codearena.Domain.model.Participacion;
import com.example.codearena.Domain.model.Reto;
import com.example.codearena.Domain.model.Usuario;
import com.example.codearena.Domain.port.In.*;
import com.example.codearena.Domain.port.out.ParticipacionRepositoryPort;
import com.example.codearena.Domain.port.out.RetoRepositoryPort;
import com.example.codearena.Domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ParticipacionService implements AceptarRetoUseCase, EnviarSolucionUseCase,
        AprobarSolucionUseCase, RechazarSolucionUseCase {

    private final ParticipacionRepositoryPort participacionRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final RetoRepositoryPort retoRepositoryPort;

    public ParticipacionService(ParticipacionRepositoryPort participacionRepositoryPort,
                                UsuarioRepositoryPort usuarioRepositoryPort,
                                RetoRepositoryPort retoRepositoryPort) {
        this.participacionRepositoryPort = participacionRepositoryPort;
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.retoRepositoryPort = retoRepositoryPort;
    }

    @Override
    public Participacion aceptarReto(Long usuarioId, Long retoId) {

        Usuario usuario = usuarioRepositoryPort.buscarPorId(usuarioId)
                .orElseThrow(() -> new UsuarioNoEncontradoException(usuarioId));

        Reto reto = retoRepositoryPort.buscarPorId(retoId)
                .orElseThrow(() -> new RetoNoEncontradoException(retoId));

        if (participacionRepositoryPort.existeParticipacionActiva(usuarioId, retoId)) {
            throw new ParticipacionActivaExistenteException(usuarioId, retoId);
        }

        Participacion nuevaParticipacion = new Participacion(usuario, reto);
        return participacionRepositoryPort.guardar(nuevaParticipacion);
    }

    @Override
    public Participacion enviarSolucion(Long usuarioId, Long retoId, String solucion) {

        Participacion participacion = participacionRepositoryPort
                .buscarParticipancioActiva(usuarioId, retoId)
                .orElseThrow(() -> new ParticipacionNoEncontradaException(usuarioId, retoId));

        participacion.enviarSolucion(solucion);

        return participacionRepositoryPort.guardar(participacion);
    }

    @Override
    public Participacion aprobarSolucion(Long participacionId) {

        Participacion participacion = participacionRepositoryPort.buscarPorId(participacionId)
                .orElseThrow(() -> new ParticipacionNoEncontradaException(participacionId));

        participacion.aprobar();

        Usuario usuario = participacion.getUsuario();
        usuario.sumarExperiencia(participacion.getExperienciaObtenida());
        usuarioRepositoryPort.guardar(usuario);

        return participacionRepositoryPort.guardar(participacion);
    }

    @Override
    public Participacion rechazarSolucion(Long participacionId) {

        Participacion participacion = participacionRepositoryPort.buscarPorId(participacionId)
                .orElseThrow(() -> new ParticipacionNoEncontradaException(participacionId));

        participacion.rechazar();

        return participacionRepositoryPort.guardar(participacion);
    }
}