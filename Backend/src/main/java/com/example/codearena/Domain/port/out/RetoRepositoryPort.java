package com.example.codearena.Domain.port.out;

import com.example.codearena.Domain.model.Dificultad;
import com.example.codearena.Domain.model.Reto;
import com.example.codearena.Domain.model.utils.PaginaSolicitud;
import com.example.codearena.Domain.model.utils.ResultadoPaginado;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RetoRepositoryPort {

    Reto guardar(Reto reto);

    Optional<Reto> buscarPorId(Long id);

    ResultadoPaginado<Reto> buscarTodos(PaginaSolicitud solicitud);

    ResultadoPaginado<Reto> buscarTodosActivos(PaginaSolicitud solicitud);

    ResultadoPaginado<Reto> buscarPorDificultad(Dificultad dificultad, PaginaSolicitud solicitud);

    ResultadoPaginado<Reto> buscarPorCategoria(Long categoriaId, PaginaSolicitud solicitud);

    ResultadoPaginado<Reto> buscarProximosAVencer(LocalDateTime antesDe, PaginaSolicitud solicitud);
}
