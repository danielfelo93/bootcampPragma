package com.bootcamp.emazonapi.domain.api;

import com.bootcamp.emazonapi.domain.service.Marca;

import java.util.List;

public interface IMarcaServicePort {

    void guardarMarca(Marca marca);

    Marca obtenerMarca(String nombre);

    List<Marca> listarMarcas(int page, int size, String order);


}

