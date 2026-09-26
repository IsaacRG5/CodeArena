package com.example.codearena.Domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reto {

    private Long id;
    private String titulo;
    private String descripcion;
    private Categorias categoria;
    private Dificultad dificultad;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime FechaLimite;
    private EstadoReto estado;

    public Reto(String titulo, String descripcion, String categoria,
                String dificultad, LocalDateTime fechaLimite) {

        this.id=null;
        this.titulo = requireNoVacio(titulo, "El titulo es obligatorio ");
        this.descripcion = descripcion;
        this.categoria = requireNoVacio(categoria, "La categoria es obligatoria");
        this.dificultad = Objects.requireNonNull(dificultad, "La dificultad es obligatoria");
        this.fechaCreacion = LocalDateTime.now();
        this.FechaLimite = fechaLimite;
        this.estado = EstadoReto.ACTIVO;
    }

    public Reto(Long id, String titulo, String descripcion , Categorias categoria, Dificultad dificultad
    LocalDateTime fechaCreacion, LocalDateTime fechaLimite, EstadoReto estado){
        this.id = Objects.requireNonNull(id, "El id es obligatorio al reconstruir un reto");
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.dificultad = dificultad;
        this.fechaCreacion = fechaCreacion;
        this.FechaLimite = fechaLimite;
        this.estado = estado;
    }

    public static String requireNoVacio(String valor, String mensaje) {
        if(valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    private static LocalDateTime validarFechaLimite(LocalDateTime fechaLimite, LocalDateTime fechaCreacion) {
        if (fechaLimite == null || !fechaLimite.isAfter(fechaCreacion)) {
            throw new IllegalArgumentException("La fecha límite debe ser posterior a la fecha de creación");
        }
        return fechaLimite;
    }

    public int getExperienciaOtorgada() {
        return dificultad.getExperienciaOtorgada();
    }

    public boolean estaVencido() {
        return LocalDateTime.now().isAfter(fechaLimite);
    }

    public boolean estaActivo() {
        return estado == EstadoReto.ACTIVO;
    }

    public boolean puedeAceptarse() {
        return estaActivo() && !estaVencido();
    }

    public void activar() {
        this.estado = EstadoReto.ACTIVO;
    }

    public void desactivar() {
        this.estado = EstadoReto.INACTIVO;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public EstadoReto getEstado() {
        return estado;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Reto other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
