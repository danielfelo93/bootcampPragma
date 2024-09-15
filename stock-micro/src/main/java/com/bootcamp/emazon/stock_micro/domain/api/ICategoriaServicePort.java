package com.bootcamp.emazon.stock_micro.domain.api;


import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

public interface ICategoriaServicePort {

    void guardarCategoria(Categoria categoria);

    PagedResponse<Categoria> listarCategorias(int page, int size, String order);


}

