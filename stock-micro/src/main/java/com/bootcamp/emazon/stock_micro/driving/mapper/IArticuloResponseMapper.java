package com.bootcamp.emazon.stock_micro.driving.mapper;


import com.bootcamp.emazon.stock_micro.domain.service.Articulo;
import com.bootcamp.emazon.stock_micro.driving.dto.response.ArticuloResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticuloResponseMapper {

    ArticuloResponse articuloToResponse(Articulo articulo);
    PagedResponse<ArticuloResponse> articuloToResponseList(PagedResponse<Articulo> articulos);

}

