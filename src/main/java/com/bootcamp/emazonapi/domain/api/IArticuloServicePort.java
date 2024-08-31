package com.bootcamp.emazonapi.domain.api;

import com.bootcamp.emazonapi.domain.service.Articulo;

import java.util.List;

public interface IArticuloServicePort {

    void guardarArticulo(Articulo articulo, Long marcaId, List<Long> categoriaIds);

    Articulo obtenerArticulo(String nombre);

    List<Articulo> listarArticulos(int page, int size, String order);


}

