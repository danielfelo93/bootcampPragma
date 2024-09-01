package com.bootcamp.emazonapi.domain.spi;

import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

import java.util.List;

public interface IArticuloPersistencePort {

    void guardarArticulo(Articulo articulo);

    Articulo obtenerArticulo(String nombre);

    PagedResponse<Articulo> listarArticulos(int page, int size, String order);

    PagedResponse<Articulo> listarArticulosPorCategoria(String nombreCategoria, int page, int size, String order);

    PagedResponse<Articulo> listarArticulosPorMarca(String nombreMarca, int page, int size, String order);

}

