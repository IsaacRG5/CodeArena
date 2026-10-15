package com.example.codearena.Infrastructure.adapter.out.persistence;

import com.example.codearena.Domain.model.Dificultad;
import com.example.codearena.Domain.model.EstadoReto;
import com.example.codearena.Domain.model.EstadoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @Enumerated(EnumType.STRING)
    @Column
    private Dificultad dificultad;


    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDateTime fechaLimite;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadoReto estado;
}