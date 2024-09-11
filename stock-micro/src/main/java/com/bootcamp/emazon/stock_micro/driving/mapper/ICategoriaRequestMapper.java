package com.bootcamp.emazonapi.driving.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.driving.dto.request.AddCategoriaRequest;

@Mapper(componentModel = "spring")
public interface ICategoriaRequestMapper {

    @Mapping(target = "id", ignore = true)
    Categoria addRequestToCategoria(AddCategoriaRequest addCategoriaRequest);

}