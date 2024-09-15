package com.bootcamp.emazon.transaccion_micro.domain.api;

import com.bootcamp.emazon.transaccion_micro.domain.service.Articulo;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;

public interface IArticuloServicePort {

    void guardarArticulo(Articulo articulo);

    Articulo obtenerArticulo(String nombre);

    PagedResponse<Articulo> listarArticulos(int page, int size, String order);

}

