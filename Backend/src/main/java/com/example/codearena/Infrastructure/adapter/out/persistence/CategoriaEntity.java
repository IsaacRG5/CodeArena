package com.example.codearena.Infrastructure.adapter.out.persistence;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
}
