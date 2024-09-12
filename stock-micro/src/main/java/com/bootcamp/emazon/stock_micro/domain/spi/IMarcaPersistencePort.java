package com.bootcamp.emazon.stock_micro.domain.spi;

import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

import java.util.Optional;

public interface IMarcaPersistencePort {

    void guardarMarca(Marca marca);

    Marca obtenerMarca(String nombre);

    Optional<Marca> obtenerMarcaPorId(Long id);

    PagedResponse<Marca> listarMarcas(int page, int size, String order);

}

