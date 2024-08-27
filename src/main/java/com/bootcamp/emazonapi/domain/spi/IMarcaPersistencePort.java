package com.bootcamp.emazonapi.domain.spi;

import com.bootcamp.emazonapi.domain.service.Marca;

import java.util.List;

public interface IMarcaPersistencePort {

    void guardarMarca(Marca marca);

    Marca obtenerMarca(String nombre);

    List<Marca> listarMarcas(int page, int size, String order);

}

