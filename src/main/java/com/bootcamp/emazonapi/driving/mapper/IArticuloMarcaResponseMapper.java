package com.bootcamp.emazonapi.driving.mapper;

import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.driving.dto.response.ArticuloCategoriaResponse;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IArticuloMarcaResponseMapper {

    ArticuloCategoriaResponse articuloMarcaToResponse(Articulo articulo);
    PagedResponse<ArticuloCategoriaResponse> articuloMarcaToResponseList(PagedResponse<Articulo> articulos);

}

