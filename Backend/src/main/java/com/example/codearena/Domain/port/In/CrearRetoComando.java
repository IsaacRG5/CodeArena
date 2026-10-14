package com.example.codearena.Domain.port.In;

import com.example.codearena.Domain.model.Dificultad;

import java.time.LocalDateTime;

public record CrearRetoComando(
        String titulo,
        String descripcion,
        Long categoriaId,
        Dificultad dificultad,
        LocalDateTime fechaLimite
) {
}
