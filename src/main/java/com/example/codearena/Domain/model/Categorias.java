package com.example.codearena.Domain.model;

import java.util.Objects;

public class Categorias {

    private Long id;
    private String nombre;

    public Categorias(String nombre) {
        this.id =null;
        this.nombre = requireNoVacio(nombre);

    }

    public Categorias(Long id, String nombre) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio al construir una categoria");
        this.nombre = nombre;
    }

    private static String requireNoVacio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        return nombre;
    }

    public Long getId() {return id;}
    public String getNombre() {return nombre;}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if(!(object instanceof Categorias other )) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
