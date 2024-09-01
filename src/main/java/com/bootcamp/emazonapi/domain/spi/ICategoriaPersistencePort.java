package com.bootcamp.emazonapi.domain.spi;

import java.util.List;
import java.util.Optional;

import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

public interface ICategoriaPersistencePort {

    void guardarCategoria(Categoria categoria);

    Categoria obtenerCategoria(String nombre);

    Optional<Categoria> obtenerCategoriaPorId(Long id);

    PagedResponse<Categoria> listarCategorias(int page, int size, String order);

}

