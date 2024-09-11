package com.bootcamp.emazonapi.domain.api;

import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

import java.util.List;

public interface IMarcaServicePort {

    void guardarMarca(Marca marca);

    Marca obtenerMarca(String nombre);

    PagedResponse<Marca> listarMarcas(int page, int size, String order);


}

