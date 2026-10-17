package com.example.codearena.Domain.exception;

public class CategoriaNoEncontradaException extends RuntimeException {
    public CategoriaNoEncontradaException(Long id) {
        super("No se encontró la categoria con id: " + id);
    }
}
