package com.example.codearena.Infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

}
