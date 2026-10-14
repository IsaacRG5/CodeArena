package com.example.codearena.Domain.port.In;

import com.example.codearena.Domain.model.Participacion;

public interface RechazarSolucionUseCase {

    Participacion rechazarSolucion(Long ParticipacionId);
}
