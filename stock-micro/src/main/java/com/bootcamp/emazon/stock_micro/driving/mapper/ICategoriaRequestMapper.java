package com.bootcamp.emazon.stock_micro.driving.mapper;

import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.driving.dto.request.AddCategoriaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ICategoriaRequestMapper {

    @Mapping(target = "id", ignore = true)
    Categoria addRequestToCategoria(AddCategoriaRequest addCategoriaRequest);

}