package com.bootcamp.emazon.transaccion_micro.driving.mapper;

import com.bootcamp.emazon.transaccion_micro.domain.service.Articulo;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.ArticuloResponse;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IArticuloResponseMapper {

    ArticuloResponse articuloToResponse(Articulo articulo);
    PagedResponse<ArticuloResponse> articuloToResponseList(PagedResponse<Articulo> articulos);

}

