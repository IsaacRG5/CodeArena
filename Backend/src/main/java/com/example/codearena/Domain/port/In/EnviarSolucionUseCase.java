package com.example.codearena.Domain.port.In;

import com.example.codearena.Domain.model.Participacion;

public interface EnviarSolucionUseCase {

    Participacion enviarSolucion(Long usuarioId, Long retoId, String solucion );
}
