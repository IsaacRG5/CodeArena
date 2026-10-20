package com.example.codearena.Infrastructure.adapter.in.web.dto;

import com.example.codearena.Domain.model.Dificultad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CrearRetoRequest(

        @NotBlank(message = "El título es obligatorio")
        String titulo,

        String descripcion,

        @NotNull(message = "La categoría es obligatoria")
        Long categoriaId,

        @NotNull(message = "La dificultad es obligatoria")
        Dificultad dificultad,

        @NotNull(message = "La fecha límite es obligatoria")
        @Future(message = "La fecha límite debe ser en el futuro")
        LocalDateTime fechaLimite
) {}
