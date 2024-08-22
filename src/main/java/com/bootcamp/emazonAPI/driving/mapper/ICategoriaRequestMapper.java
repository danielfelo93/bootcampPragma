package com.bootcamp.emazonAPI.driving.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bootcamp.emazonAPI.domain.service.Categoria;
import com.bootcamp.emazonAPI.driving.dto.request.AddCategoriaRequest;

@Mapper(componentModel = "spring")
public interface ICategoriaRequestMapper {

    @Mapping(target = "id", ignore = true)
    Categoria addRequestToCategoria(AddCategoriaRequest addCategoriaRequest);

}