package com.example.codearena.Domain.exception;

public class   ParticipacionNoEncontradaException extends RuntimeException {
    public ParticipacionNoEncontradaException(Long id) {
        super("No se encontró una participación con id: " + id);
    }

    public ParticipacionNoEncontradaException(Long usuarioId, Long retoId) {
        super("No se encontró una participación activa del usuario " + usuarioId + " para el reto " + retoId);
    }
}
