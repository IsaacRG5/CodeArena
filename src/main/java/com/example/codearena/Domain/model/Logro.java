package com.example.codearena.Domain.model;

import java.util.Objects;

public class Logro {

    private final Long id;
    private String nombre;
    private String descripcion;

    public Logro(String nombre, String descripcion) {
        this.id = null;
        this.nombre = requierNoVacio(nombre);
        this.descripcion = descripcion;
    }

    public Logro(Long id, String nombre, String descripcion, Nivel nivel) {
        this.id = Objects.requireNonNull(id, "El Id es obligatorio al construir un logro.");
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    private static String requierNoVacio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del logro es obligatorio.");
        }
        return nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Logro)) return false;
        return id != null && id.equals(((Logro) object).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
