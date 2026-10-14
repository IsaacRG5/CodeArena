package com.example.codearena.Domain.port.out;

import com.example.codearena.Domain.model.Usuario;

public interface TokenProviderPort {


    String generarToken(Usuario usuario);
}
