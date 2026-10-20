package com.example.codearena.Infrastructure.adapter.in.web;

import com.example.codearena.Domain.model.Reto;
import com.example.codearena.Domain.port.In.CrearRetoComando;
import com.example.codearena.Domain.port.In.CrearRetoUseCase;
import com.example.codearena.Infrastructure.adapter.in.web.dto.CrearRetoRequest;
import jakarta.validation.Valid;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/retos")
public class RetoController {

    private final CrearRetoUseCase crearRetoUseCase;

    public RetoController (CrearRetoUseCase crearRetoUseCase){
        this.crearRetoUseCase = crearRetoUseCase;
    }

    @PostMapping
    public ResponseEntity<Reto> crear (@Valid @RequestBody CrearRetoRequest request){

        CrearRetoComando comando = new CrearRetoComando(
               request.titulo(),
               request.descripcion(),
               request.categoriaId(),
                request.dificultad(),
                request.fechaLimite()
        );

        Reto reto = crearRetoUseCase.crearReto(comando);

        return  ResponseEntity.status(HttpStatus.CREATED).body(reto);
    }

}
