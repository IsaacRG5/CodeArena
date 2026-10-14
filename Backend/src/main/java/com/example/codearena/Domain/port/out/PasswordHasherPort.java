package com.example.codearena.Domain.port.out;

public interface PasswordHasherPort {

    String codificar(String contrasenaPlano);

    boolean verificar(String contrasenaPlano, String contrasenaHasheada);
}
