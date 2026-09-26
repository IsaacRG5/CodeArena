package com.example.codearena.Domain.model;


import java.util.Objects;

public class Usuario {

    private final Long id;
    private final String nombre;
    private final String username;
    private final String email;
    private final String password;
    private final Rol rol;
    private Nivel nivel;
    private int experienciaAcumulada;
    private EstadoUsuario estado;

    // Constructor para usuarios nuevos (sin id)
    public Usuario(String nombre, String username, String email, String password, Rol rol) {
        this.id = null;
        this.nombre = requireNoVacio(nombre, "El nombre es obligatorio");
        this.username = requireNoVacio(username, "El username es obligatorio");
        this.email = requireNoVacio(email, "El email es obligatorio");
        this.password = requireNoVacio(password, "La contraseña es obligatoria");
        this.rol = Objects.requireNonNull(rol, "El rol es obligatorio");
        this.nivel = Nivel.ROOKIE;
        this.experienciaAcumulada = 0;
        this.estado = EstadoUsuario.ACTIVO;
    }

    public Usuario(Long id, String nombre, String username, String email, String password,
                   Rol rol, Nivel nivel, int experienciaAcumulada, EstadoUsuario estado) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio");
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.nivel = nivel;
        this.experienciaAcumulada = experienciaAcumulada;
        this.estado = estado;
    }

    private static String requireNoVacio(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    public void sumarExperiencia(int xp) {
        if (xp <= 0) {
            throw new IllegalArgumentException("La experiencia a sumar debe ser positiva");
        }
        this.experienciaAcumulada += xp;
        this.nivel = Nivel.calcucalarSegunExperiencia(this.experienciaAcumulada);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Usuario other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}