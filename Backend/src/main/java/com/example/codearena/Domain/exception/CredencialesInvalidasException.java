package com.example.codearena.Domain.exception;

public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException() {
        super("Email o Contraseña incorrecta: " );
    }
}
