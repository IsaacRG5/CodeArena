package com.example.codearena.Domain.port.out;

import com.example.codearena.Domain.model.LogroObtenido;

import java.util.List;

public interface LogroObtenidoRepositoryPort {

    LogroObtenido guardar(LogroObtenido logroObtenido);

    boolean existeLogroObtenido(LogroObtenido logroObtenido);

    List<LogroObtenido> buscarPorUsuario(Long usuarioId);
}
