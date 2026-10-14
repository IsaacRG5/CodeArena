package com.example.codearena.Domain.port.In;

import com.example.codearena.Domain.model.Reto;

public interface CrearRetoUseCase {
    Reto crearReto(CrearRetoComando crearRetoComando);
}
