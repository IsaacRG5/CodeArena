package com.example.codearena.Infrastructure.exception;

import com.example.codearena.Domain.exception.CategoriaNoEncontradaException;
import com.example.codearena.Domain.exception.ParticipacionNoEncontradaException;
import com.example.codearena.Domain.exception.RetoNoEncontradoException;
import com.example.codearena.Domain.exception.UsuarioNoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({RetoNoEncontradoException.class,
            CategoriaNoEncontradaException.class,
            ParticipacionNoEncontradaException.class,
            UsuarioNoEncontradoException.class})
    public ResponseEntity<ErrorResponse> handleNoEncontrado(RuntimeException ex) {
        ErrorResponse body = new ErrorResponse(
                Instant.now(),
                404,
                "Not Found",
                ex.getMessage(),
                List.of()
        );
        return ResponseEntity.status(404).body(body);
    }
}
