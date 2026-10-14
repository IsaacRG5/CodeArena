package com.example.codearena.Domain.port.In;

import com.example.codearena.Domain.model.Usuario;

public interface RegistrarUsuarioUseCase {
    Usuario registrarUsuario(RegistrarUsuarioComando registrarUsuarioComando);

}
