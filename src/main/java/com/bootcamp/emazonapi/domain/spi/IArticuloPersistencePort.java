package com.bootcamp.emazonapi.domain.spi;

import com.bootcamp.emazonapi.domain.service.Articulo;

import java.util.List;

public interface IArticuloPersistencePort {

    void guardarArticulo(Articulo articulo);

    Articulo obtenerArticulo(String nombre);

    List<Articulo> listarArticulos(int page, int size, String order);

}

