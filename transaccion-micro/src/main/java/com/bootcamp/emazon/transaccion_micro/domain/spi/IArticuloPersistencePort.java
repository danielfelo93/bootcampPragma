package com.bootcamp.emazon.transaccion_micro.domain.spi;


import com.bootcamp.emazon.transaccion_micro.domain.service.Articulo;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;

public interface IArticuloPersistencePort {

    void guardarArticulo(Articulo articulo);

    Articulo obtenerArticulo(String nombre);

    PagedResponse<Articulo> listarArticulos(int page, int size, String order);


}

