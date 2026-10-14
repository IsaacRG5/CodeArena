package com.example.codearena.Infrastructure.adapter.out.persistence;

import com.example.codearena.Domain.model.Usuario;
import com.example.codearena.Domain.model.utils.PaginaSolicitud;
import com.example.codearena.Domain.model.utils.ResultadoPaginado;
import com.example.codearena.Domain.port.out.UsuarioRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final  UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository JpaRepository) {
        this.usuarioJpaRepository = JpaRepository;
    }

    private UsuarioEntity toEntity(Usuario usuario) {
        return UsuarioEntity.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .username(usuario.getUsername())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .rol(usuario.getRol())
                .nivel(usuario.getNivel())
                .experienciaAcumulada(usuario.getExperienciaAcumulada())
                .estado(usuario.getEstado())
                .build();
    }

    private Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRol(),
                entity.getNivel(),
                entity.getExperienciaAcumulada(),
                entity.getEstado()
        );

    }

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity usuarioEntity = toEntity(usuario);
        UsuarioEntity guardado = usuarioJpaRepository.save(usuarioEntity);
        return toDomain(guardado);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioJpaRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioJpaRepository.findByUsername(username)
                .map(this::toDomain);
    }

    @Override
    public boolean existePorEmail( String email){
        return usuarioJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existePorUsername (String username){
        return usuarioJpaRepository.existsByUsername(username);
    }

    @Override
    public ResultadoPaginado<Usuario> buscarTodos(PaginaSolicitud solicitud) {
        Pageable pageable = PageRequest.of(solicitud.pagina(), solicitud.tamano());
        Page<UsuarioEntity> pagina = usuarioJpaRepository.findAll(pageable);

        List<Usuario> usuarios = pagina.getContent().stream()
                .map(this::toDomain)
                .toList();

        return new ResultadoPaginado<>(
                usuarios,
                pagina.getNumber(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }
}
