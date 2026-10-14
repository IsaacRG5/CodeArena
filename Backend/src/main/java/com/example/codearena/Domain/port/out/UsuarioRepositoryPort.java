package com.example.codearena.Domain.port.out;

import com.example.codearena.Domain.model.Usuario;
import com.example.codearena.Domain.model.utils.PaginaSolicitud;
import com.example.codearena.Domain.model.utils.ResultadoPaginado;

import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorUsername(String username);

    boolean existePorEmail(String email);

    boolean existePorUsername(String username);

    ResultadoPaginado<Usuario> buscarTodos(PaginaSolicitud solicitud);
}
