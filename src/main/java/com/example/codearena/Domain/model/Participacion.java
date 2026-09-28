package com.example.codearena.Domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Participacion {
    private Long id;
    private Usuario usuario;
    private Reto reto;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaEntrega;
    private EstadoParticipacion estado;
    private String solucionEnviada;
    private int experienciaObtenida;

    public Participacion(Usuario usuario, Reto reto) {
        this.id = null;
        this.usuario = Objects.requireNonNull(usuario, "El Usuario es obligatorio");
        this.reto = Objects.requireNonNull(reto, "El Reto es obligatorio");
        if (!reto.puedeAceptarse()) {
            throw new IllegalStateException( "El reto no puede aceptarse: está inactivo o ya venció");
        }
        this.fechaInicio = LocalDateTime.now();
        this.fechaEntrega = null;
        this.estado = EstadoParticipacion.APPROVED;
        this.solucionEnviada = null;
        this.experienciaObtenida = 0;
    }

    public  Participacion (Long id, Usuario usuario, Reto reto, LocalDateTime fechaInicio, LocalDateTime fechaEntrega,
    EstadoParticipacion estado, String solucionEnviada, int experienciaObtenida) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio al reconstruir una participación");
        this.usuario = usuario;
        this.reto = reto;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
        this.solucionEnviada = solucionEnviada;
        this.experienciaObtenida = experienciaObtenida;
    }

    public void enviarSolucion(String solucion) {
        if (estado != EstadoParticipacion.ACCEPTED && estado != EstadoParticipacion.IN_PROGRESS){
            throw new IllegalStateException("No se puede enviar una solucion en estado " + estado);
        }
        if (solucion == null){
            throw new IllegalArgumentException("La solucion no puede ser vacia");
        }
        this.solucionEnviada = solucion;
        this.fechaEntrega = LocalDateTime.now();
        this.estado = EstadoParticipacion.SUBMITTED;
    }

    public void aprobar(){
        if (estado != EstadoParticipacion.SUBMITTED){
            throw new IllegalStateException("Solo se puede aprobar una solucion en estado SUBMITTED (Actual" + estado);
        }
        this.estado = EstadoParticipacion.APPROVED;
        this.experienciaObtenida = reto.getExperienciaOtorgada();
    }

    public void rechazar(){
        if (estado != EstadoParticipacion.SUBMITTED){
            throw new IllegalStateException("Solo se puede rechazar una participación en estado SUBMITTED (actual: " + estado + ")");
        }
        this.estado = EstadoParticipacion.REJECTED;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Reto getReto() {
        return reto;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public EstadoParticipacion getEstado() {
        return estado;
    }

    public String getSolucionEnviada() {
        return solucionEnviada;
    }

    public int getExperienciaObtenida() {
        return experienciaObtenida;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Participacion other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
