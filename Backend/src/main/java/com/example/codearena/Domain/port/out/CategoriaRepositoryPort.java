package com.example.codearena.Domain.port.out;

import com.example.codearena.Domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {

    Categoria guardar(Categoria categoria);

    Optional<Categoria> buscarPorId(Long id);

    Optional<Categoria> buscarPorNombre(String nombre);

    boolean existePorNombre(String nombre);

    List<Categoria> buscarTodos();
}
