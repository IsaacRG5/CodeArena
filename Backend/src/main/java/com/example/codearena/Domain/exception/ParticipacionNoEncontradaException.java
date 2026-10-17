package com.example.codearena.Domain.exception;

public class ParticipacionNoEncontradaException extends RuntimeException {
    public ParticipacionNoEncontradaException(Long id) {
        super("No se encontró una participación con id: " + id);
    }
}
