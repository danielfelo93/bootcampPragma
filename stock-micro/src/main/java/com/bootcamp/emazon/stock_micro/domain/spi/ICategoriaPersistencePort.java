package com.bootcamp.emazon.stock_micro.domain.spi;

import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

import java.util.Optional;

public interface ICategoriaPersistencePort {

    void guardarCategoria(Categoria categoria);

    Optional<Categoria> obtenerCategoriaPorId(Long id);

    PagedResponse<Categoria> listarCategorias(int page, int size, String order);

}

