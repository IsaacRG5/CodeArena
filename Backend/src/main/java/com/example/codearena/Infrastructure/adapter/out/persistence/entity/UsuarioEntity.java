package com.example.codearena.Infrastructure.adapter.out.persistence.entity;

import com.example.codearena.Domain.model.EstadoUsuario;
import com.example.codearena.Domain.model.Nivel;
import com.example.codearena.Domain.model.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column
    private Nivel nivel;

    @Column
    private int experienciaAcumulada;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadoUsuario  estado;
}
