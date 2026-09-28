package com.example.codearena.Domain.model.utils;

public record PaginaSolicitud(int pagina, int tamano ){
    public PaginaSolicitud{
        if(pagina <0) throw new IllegalArgumentException("la pagina no puede ser negativa");
        if (tamano <= 0 ) throw new IllegalArgumentException("la tamano no puede ser mayor que 0");
    }
}
