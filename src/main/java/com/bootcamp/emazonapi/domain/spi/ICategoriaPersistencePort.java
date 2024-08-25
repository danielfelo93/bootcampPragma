package com.bootcamp.emazonapi.domain.spi;

import java.util.List;

import com.bootcamp.emazonapi.domain.service.Categoria;

public interface ICategoriaPersistencePort {

    void guardarCategoria(Categoria categoria);

    Categoria obtenerCategoria(String nombre);

    List<Categoria> listarCategorias(int page, int size, String order);

}

