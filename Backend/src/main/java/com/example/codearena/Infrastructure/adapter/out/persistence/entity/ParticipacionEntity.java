package com.example.codearena.Infrastructure.adapter.out.persistence.entity;

import com.example.codearena.Domain.model.EstadoParticipacion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "participacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "reto_id")
    private RetoEntity reto;

    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @Column
    private LocalDateTime fechaEntrega;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadoParticipacion estado;

    @Column(columnDefinition = "TEXT")
    private String solucionEnviada;

    @Column(nullable = false)
    private int experienciaObtenida;
}
