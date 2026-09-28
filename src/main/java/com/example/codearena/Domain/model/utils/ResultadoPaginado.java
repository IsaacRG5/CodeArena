package com.example.codearena.Domain.model.utils;

import java.util.List;

public record ResultadoPaginado<T>(List<T> contenido, int paginaActual, int totalPaginas, long totalElementos) {
}