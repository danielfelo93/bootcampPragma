package com.bootcamp.emazonapi.driving.mapper;

import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.driving.dto.response.ArticuloResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticuloResponseMapper {

    ArticuloResponse articuloToResponse(Articulo articulo);
    List<ArticuloResponse> articuloToResponseList(List<Articulo> articulos);

}

