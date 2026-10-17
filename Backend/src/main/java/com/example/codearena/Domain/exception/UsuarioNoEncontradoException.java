package com.example.codearena.Domain.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id) {
        super("No se encontró un usuario con id: " + id);
    }
}