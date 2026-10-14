package com.example.codearena.Infrastructure.config;

import com.example.codearena.Domain.port.out.PasswordHasherPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasherAdapter implements PasswordHasherPort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String codificar(String contrasenaPlano) {
        return encoder.encode(contrasenaPlano);
    }

    @Override
    public boolean verificar(String contrasenaPlano, String contrasenaHasheada){
        return encoder.matches(contrasenaPlano, contrasenaHasheada);
    }
}
