package com.bootcamp.emazonapi.domain.spi;

import com.bootcamp.emazonapi.domain.service.Marca;

import java.util.List;
import java.util.Optional;

public interface IMarcaPersistencePort {

    void guardarMarca(Marca marca);

    Marca obtenerMarca(String nombre);

    Optional<Marca> obtenerMarcaPorId(Long id);

    List<Marca> listarMarcas(int page, int size, String order);

}

