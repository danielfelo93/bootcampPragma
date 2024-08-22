package com.bootcamp.emazonAPI.driving.mapper;

import java.util.List;

import org.mapstruct.Mapper;


import com.bootcamp.emazonAPI.domain.service.Categoria;
import com.bootcamp.emazonAPI.driving.dto.response.CategoriaResponse;

@Mapper(componentModel = "spring")
public interface ICategoriaResponseMapper {

    //@Mapping(target = "id", ignore = true)
    CategoriaResponse CategoriaToResponse(Categoria categoria);
    List<CategoriaResponse> CategoriaToResponseList(List<Categoria> categorias);

}

