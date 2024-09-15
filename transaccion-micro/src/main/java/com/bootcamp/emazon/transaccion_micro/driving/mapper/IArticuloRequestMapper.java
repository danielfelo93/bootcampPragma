package com.bootcamp.emazon.transaccion_micro.driving.mapper;


import com.bootcamp.emazon.transaccion_micro.domain.service.Articulo;
import com.bootcamp.emazon.transaccion_micro.driving.dto.request.AddArticuloRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IArticuloRequestMapper {

    @Mapping(target = "id", ignore = true)
    Articulo addRequestToArticulo(AddArticuloRequest addArticuloRequest);

}