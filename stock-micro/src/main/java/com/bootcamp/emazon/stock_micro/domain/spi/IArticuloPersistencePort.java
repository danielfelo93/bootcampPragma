package com.bootcamp.emazon.stock_micro.domain.spi;

import com.bootcamp.emazon.stock_micro.domain.service.Articulo;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

import java.util.Optional;

public interface IArticuloPersistencePort {

    void crearArticulo(Articulo articulo);

    void agregarArticulos(Articulo articulo);

    PagedResponse<Articulo> listarArticulos(int page, int size, String order);

    PagedResponse<Articulo> listarArticulosPorCategoria(String nombreCategoria, int page, int size, String order);

    PagedResponse<Articulo> listarArticulosPorMarca(String nombreMarca, int page, int size, String order);

    Optional<Articulo> obtenerArticuloPorId(long id);
}

