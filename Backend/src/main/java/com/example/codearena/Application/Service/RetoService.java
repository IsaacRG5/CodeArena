package com.example.codearena.Application.Service;

import com.example.codearena.Domain.exception.CategoriaNoEncontradaException;
import com.example.codearena.Domain.model.Categoria;
import com.example.codearena.Domain.model.Reto;
import com.example.codearena.Domain.port.In.CrearRetoComando;
import com.example.codearena.Domain.port.In.CrearRetoUseCase;
import com.example.codearena.Domain.port.out.CategoriaRepositoryPort;
import com.example.codearena.Domain.port.out.RetoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RetoService implements CrearRetoUseCase {

    private final RetoRepositoryPort retoRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public RetoService(RetoRepositoryPort retoRepositoryPort, CategoriaRepositoryPort categoriaRepositoryPort) {
        this.retoRepositoryPort = retoRepositoryPort;
        this.categoriaRepositoryPort = categoriaRepositoryPort;
    }

    @Override
    public Reto crearReto(CrearRetoComando comando) {
        Categoria categoria  = categoriaRepositoryPort.buscarPorId(comando.categoriaId())
                .orElseThrow(() -> new CategoriaNoEncontradaException(comando.categoriaId()));

        Reto nuevoReto = new Reto(
                comando.titulo(),
                comando.descripcion(),
                categoria,
                comando.dificultad(),
                comando.fechaLimite()

        );
        return retoRepositoryPort.guardar(nuevoReto);
    }


}