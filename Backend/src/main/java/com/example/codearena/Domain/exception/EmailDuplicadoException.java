package com.example.codearena.Domain.exception;

public class EmailDuplicadoException extends RuntimeException{

    public EmailDuplicadoException(String email){
        super("ya existe un usuario con este email: " + email);
    }
}
