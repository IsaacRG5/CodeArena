package com.example.codearena.Domain.port.In;

import com.example.codearena.Domain.model.Rol;

public record RegistrarUsuarioComando(

        String nombre,
        String username,
        String email,
        String password,
        Rol rol
) {}
