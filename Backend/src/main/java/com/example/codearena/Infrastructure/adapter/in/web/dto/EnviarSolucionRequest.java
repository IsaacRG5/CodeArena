package com.example.codearena.Infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record EnviarSolucionRequest(@NotBlank(message = "La solución no puede estar vacía")
                                     String solucion) {
}
