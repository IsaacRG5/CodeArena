package com.example.codearena.Domain.port.In;

public record AutenticarUsuarioComando(

        String email,
        String password
) {
}
