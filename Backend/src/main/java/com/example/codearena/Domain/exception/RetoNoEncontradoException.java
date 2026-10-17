package com.example.codearena.Domain.exception;

public class RetoNoEncontradoException extends RuntimeException {
    public RetoNoEncontradoException(Long id) {
        super("No se encontró un reto con id: " + id);
    }
}
