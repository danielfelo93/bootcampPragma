package com.bootcamp.emazon.stock_micro.driving.mapper;

import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.driving.dto.request.AddMarcaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMarcaRequestMapper {

    @Mapping(target = "id", ignore = true)
    Marca addRequestToMarca(AddMarcaRequest addMarcaRequest);

}