package com.bootcamp.emazon.stock_micro.driving.mapper;


import com.bootcamp.emazon.stock_micro.domain.service.Articulo;
import com.bootcamp.emazon.stock_micro.driving.dto.request.AddArticuloRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IArticuloRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "marca", ignore = true)
    Articulo addRequestToArticulo(AddArticuloRequest addArticuloRequest);

}