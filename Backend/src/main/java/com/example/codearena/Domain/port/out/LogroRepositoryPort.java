package com.example.codearena.Domain.port.out;

import com.example.codearena.Domain.model.Logro;

import java.util.List;
import java.util.Optional;

public interface LogroRepositoryPort {
    Logro guardar(Logro logro);

    Optional<Logro> buscarPorId(Long id);

    Optional<Logro> buscarPorNombre(String nombre);

    boolean existePorNombre(String nombre);

    List<Logro> buscarTodos();
}
