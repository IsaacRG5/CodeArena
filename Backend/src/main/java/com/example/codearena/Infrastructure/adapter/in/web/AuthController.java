package com.example.codearena.Infrastructure.adapter.in.web;

import com.example.codearena.Domain.model.Rol;
import com.example.codearena.Domain.model.Usuario;
import com.example.codearena.Domain.port.In.AutenticarUsuarioComando;
import com.example.codearena.Domain.port.In.AutenticarUsuarioUseCase;
import com.example.codearena.Domain.port.In.RegistrarUsuarioComando;
import com.example.codearena.Domain.port.In.RegistrarUsuarioUseCase;
import com.example.codearena.Infrastructure.adapter.in.web.dto.LoginRequest;
import com.example.codearena.Infrastructure.adapter.in.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;
    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;

    public AuthController(RegistrarUsuarioUseCase registrarUsuarioUseCase,
                           AutenticarUsuarioUseCase autenticarUsuarioUseCase) {
        this.registrarUsuarioUseCase = registrarUsuarioUseCase;
        this.autenticarUsuarioUseCase = autenticarUsuarioUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@Valid @RequestBody RegisterRequest request) {

        RegistrarUsuarioComando comando = new RegistrarUsuarioComando(
                request.nombre(),
                request.username(),
                request.email(),
                request.password(),
                Rol.PLAYER
        );

        Usuario usuario = registrarUsuarioUseCase.registrarUsuario(comando);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {

        AutenticarUsuarioComando comando = new AutenticarUsuarioComando(
                request.email(),
                request.password()
        );

        String token = autenticarUsuarioUseCase.autenticar(comando);
        return ResponseEntity.ok(token);
    }
}
