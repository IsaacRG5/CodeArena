package com.example.codearena.Domain.exception;

public class UsernameDuplicadoException extends RuntimeException {
    public UsernameDuplicadoException(String username) {
        super("ya existe un usuario con este username: " + username);
    }
}
