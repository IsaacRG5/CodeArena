package com.example.codearena.Domain.port.In;

import com.example.codearena.Domain.model.Participacion;

public interface AceptarRetoUseCase {

    Participacion aceptarReto(Long usuarioId, Long retoId);
}
