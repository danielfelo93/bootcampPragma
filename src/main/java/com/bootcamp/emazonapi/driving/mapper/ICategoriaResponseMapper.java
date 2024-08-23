package com.bootcamp.emazonapi.driving.mapper;

import java.util.List;

import org.mapstruct.Mapper;


import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.driving.dto.response.CategoriaResponse;

@Mapper(componentModel = "spring")
public interface ICategoriaResponseMapper {

    //@Mapping(target = "id", ignore = true)
    CategoriaResponse categoriaToResponse(Categoria categoria);
    List<CategoriaResponse> categoriaToResponseList(List<Categoria> categorias);

}

