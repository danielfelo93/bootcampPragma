package com.bootcamp.emazonAPI.domain.spi;

import java.util.List;

import com.bootcamp.emazonAPI.domain.service.Categoria;

public interface ICategoriaPersistencePort {

    void guardarCategoria(Categoria categoria);

    Categoria obtenerCategoria(String nombre);

    List<Categoria> listarCategorias(int page, int size);

    List<Categoria> listarCategoriasfree();

}

