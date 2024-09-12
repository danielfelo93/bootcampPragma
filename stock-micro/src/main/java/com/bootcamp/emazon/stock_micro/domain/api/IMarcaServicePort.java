package com.bootcamp.emazon.stock_micro.domain.api;

import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

public interface IMarcaServicePort {

    void guardarMarca(Marca marca);

    Marca obtenerMarca(String nombre);

    PagedResponse<Marca> listarMarcas(int page, int size, String order);


}

