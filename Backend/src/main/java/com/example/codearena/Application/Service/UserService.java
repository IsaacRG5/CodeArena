package com.example.codearena.Application.Service;


import com.example.codearena.Domain.exception.CredencialesInvalidasException;
import com.example.codearena.Domain.exception.EmailDuplicadoException;
import com.example.codearena.Domain.exception.UsernameDuplicadoException;
import com.example.codearena.Domain.model.Usuario;
import com.example.codearena.Domain.port.In.AutenticarUsuarioComando;
import com.example.codearena.Domain.port.In.AutenticarUsuarioUseCase;
import com.example.codearena.Domain.port.In.RegistrarUsuarioComando;
import com.example.codearena.Domain.port.In.RegistrarUsuarioUseCase;
import com.example.codearena.Domain.port.out.PasswordHasherPort;
import com.example.codearena.Domain.port.out.TokenProviderPort;
import com.example.codearena.Domain.port.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;

@Service
public class UserService implements RegistrarUsuarioUseCase, AutenticarUsuarioUseCase {

    private final TokenProviderPort tokenProviderPort;

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    private final PasswordHasherPort passwordHasherPort;

    public UserService(UsuarioRepositoryPort usuarioRepositoryPort,
                       TokenProviderPort tokenProviderPort,
                       PasswordHasherPort passwordHasherPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
        this.tokenProviderPort = tokenProviderPort;
        this.passwordHasherPort = passwordHasherPort;

    }

    @Override
    public Usuario registrarUsuario(RegistrarUsuarioComando Comando) {
        if (usuarioRepositoryPort.existePorEmail(Comando.email())){
            throw new EmailDuplicadoException(Comando.email());
        }

        if (usuarioRepositoryPort.existePorUsername(Comando.username())){
            throw new UsernameDuplicadoException(Comando.username());
        }

        String passwordHasheada = passwordHasherPort.codificar(Comando.password());

        Usuario nuevoUsuario = new Usuario(
                Comando.nombre(),
                Comando.username(),
                Comando.email(),
                passwordHasheada,
                Comando.rol()
        );
        return usuarioRepositoryPort.guardar(nuevoUsuario);
    }

    @Override
    public String autenticar(AutenticarUsuarioComando comando) {

        Usuario usuario = usuarioRepositoryPort.buscarPorEmail(comando.email())
                .orElseThrow(CredencialesInvalidasException::new);

        if (!passwordHasherPort.verificar(comando.password(), usuario.getPassword())) {
            throw new CredencialesInvalidasException();
        }

        return tokenProviderPort.generarToken(usuario);
    }
}
