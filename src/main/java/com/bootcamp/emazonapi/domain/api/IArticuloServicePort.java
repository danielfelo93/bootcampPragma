package com.bootcamp.emazonapi.domain.api;

import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

import java.util.List;

public interface IArticuloServicePort {

    void guardarArticulo(Articulo articulo, Long marcaId, List<Long> categoriaIds);

    Articulo obtenerArticulo(String nombre);

    PagedResponse<Articulo> listarArticulos(int page, int size, String order);

    PagedResponse<Articulo> listarArticulosPorCategoria(String nombreCategoria, int page, int size, String order);
    PagedResponse<Articulo> listarArticulosPorMarca(String nombreMarca, int page, int size, String order);
}

