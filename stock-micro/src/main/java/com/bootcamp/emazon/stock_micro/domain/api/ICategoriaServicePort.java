package com.bootcamp.emazonapi.domain.api;

import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

public interface ICategoriaServicePort {

    void guardarCategoria(Categoria categoria);

    PagedResponse<Categoria> listarCategorias(int page, int size, String order);


}

