package com.example.codearena.Infrastructure.adapter.out.persistence.adapter;

import com.example.codearena.Domain.model.Categoria;
import com.example.codearena.Domain.port.out.CategoriaRepositoryPort;
import com.example.codearena.Infrastructure.adapter.out.persistence.repository.CategoriaJpaRepository;
import com.example.codearena.Infrastructure.adapter.out.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository categoriaJpaRepository;

    public CategoriaRepositoryAdapter(CategoriaJpaRepository categoriaJpaRepository) {
        this.categoriaJpaRepository = categoriaJpaRepository;
    }

    private CategoriaEntity toEntity(Categoria categoria) {
        return CategoriaEntity.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .build();
    }

    private Categoria toDomain(CategoriaEntity categoriaEntity) {
        return new Categoria(
                categoriaEntity.getId(),
                categoriaEntity.getNombre());
    }
    @Override
    public Categoria guardar(Categoria categoria) {
        CategoriaEntity categoriaEntity = toEntity(categoria);
        CategoriaEntity guardar = categoriaJpaRepository.save(categoriaEntity);
        return toDomain(guardar);
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Categoria> buscarPorNombre(String nombre) {
        return categoriaJpaRepository.findByNombre(nombre)
                .map(this::toDomain);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return categoriaJpaRepository.existsByNombre(nombre);

    }

    @Override
    public List<Categoria> buscarTodos() {
     List<CategoriaEntity> categoriaEntities = categoriaJpaRepository.findAll();
     List<Categoria> categorias = new ArrayList<>();

        for (CategoriaEntity entity : categoriaEntities) {
            categorias.add(toDomain(entity));
        }

        return categorias;
    }
}
