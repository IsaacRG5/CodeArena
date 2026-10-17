package com.example.codearena.Domain.exception;

public class ParticipacionActivaExistenteException extends RuntimeException {
    public ParticipacionActivaExistenteException(Long usuarioId, Long retoId) {
        super("El usuario " + usuarioId + " ya tiene una participación activa en el reto " + retoId);;
    }
}
