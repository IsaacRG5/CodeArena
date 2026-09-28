package com.example.codearena.Domain.model;

import java.util.Objects;

public class Categoria {

    private final Long id;
    private String nombre;

    public Categoria( String nombre) {
        this.id = null;
        this.nombre = requiereNoVacio(nombre);
    }

    public Categoria(Long id, String nombre) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio al construir una categoria");
        this.nombre = nombre;
    }

    private static String requiereNoVacio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoria es obligatorio");
        }
        return nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Categoria)) return false;
        return id != null && id.equals(((Categoria) object).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
