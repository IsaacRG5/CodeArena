package com.example.codearena.Infrastructure.adapter.in.web;


import com.example.codearena.Domain.model.Participacion;
import com.example.codearena.Domain.port.In.*;
import com.example.codearena.Domain.port.out.UsuarioRepositoryPort;
import com.example.codearena.Infrastructure.adapter.in.web.dto.EnviarSolucionRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participaciones")
public class ParticipacionController {

    private final AceptarRetoUseCase aceptarRetoUseCase;
    private final EnviarSolucionUseCase enviarSolucionUseCase;
    private final AprobarSolucionUseCase aprobarSolucionUseCase;
    private final RechazarSolucionUseCase rechazarSolucionUseCase;
    private final UsuarioRepositoryPort usuarioRepositoryPort;


    public ParticipacionController (AceptarRetoUseCase aceptarRetoUseCase,
                                    EnviarSolucionUseCase enviarSolucionUseCase,
                                    AprobarSolucionUseCase aprobarSolucionUseCase,
                                    RechazarSolucionUseCase rechazarSolucionUseCase,
                                    UsuarioRepositoryPort usuarioRepositoryPort){

        this.aceptarRetoUseCase = aceptarRetoUseCase;
        this.enviarSolucionUseCase = enviarSolucionUseCase;
        this.aprobarSolucionUseCase =  aprobarSolucionUseCase;
        this.rechazarSolucionUseCase = rechazarSolucionUseCase;
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    private Long usuarioIdAutenticado(Authentication authentication) {
        String email = authentication.getName();
        return usuarioRepositoryPort.buscarPorEmail(email)
                .orElseThrow(() -> new IllegalStateException("Usuario autenticado no encontrado: " + email))
                .getId();

    }

    @PostMapping("/retos/{retoId}/aceptar")
    public ResponseEntity<Participacion> aceptar (@PathVariable Long retoId, Authentication authentication){
        Long usuarioId = usuarioIdAutenticado(authentication);
        Participacion participacion = aceptarRetoUseCase.aceptarReto(usuarioId, retoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(participacion);
    }

    @PostMapping("/retos/{retoId}/solucion")
    public ResponseEntity<Participacion> enviarsolucion (@PathVariable Long retoId,
                                                   @Valid @RequestBody EnviarSolucionRequest request,
                                                         Authentication authentication ){
        Long usuarioId = usuarioIdAutenticado(authentication);
        Participacion participacion = enviarSolucionUseCase.enviarSolucion(usuarioId, retoId, request.solucion());
        return ResponseEntity.ok(participacion);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{participacionId}/aprobar")
    public ResponseEntity<Participacion> aprobar(@PathVariable Long participacionId) {
        return ResponseEntity.ok(aprobarSolucionUseCase.aprobarSolucion(participacionId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{participacionId}/rechazar")
    public ResponseEntity<Participacion> rechazar(@PathVariable Long participacionId) {
        return ResponseEntity.ok(rechazarSolucionUseCase.rechazarSolucion(participacionId));
    }


}
