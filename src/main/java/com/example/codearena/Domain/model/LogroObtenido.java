package com.example.codearena.Domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class LogroObtenido {

    private final Long id;
    private final Usuario usuario;
    private final Logro logro ;
    private final LocalDateTime fechaObtencion;

    public LogroObtenido (Long id, Usuario usuario, Logro logro, LocalDateTime fechaObtencion) {
        this.id = null;
        this.usuario = Objects.requireNonNull(usuario, "El usuario es obligatorio");
        this.logro = Objects.requireNonNull(logro, "El logro es obligatorio");
        this.fechaObtencion = LocalDateTime.now();
    }

    public LogroObtenido (Long id, Usuario usuario, Logro logro, LocalDateTime fechaObtencion) {
        this.id = Objects.requireNonNull(id, "El id es obligatorio al construir el logro obtenido");
        this.usuario = usuario;
        this.logro = logro;
        this.fechaObtencion = fechaObtencion;
    }

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Logro getLogro(){
        return logro;
    }

    public LocalDateTime getFechaObtencion() {
        return fechaObtencion;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof LogroObtenido)) {
            return false;
        }
        return id != null && id.equals(((LogroObtenido) object).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
