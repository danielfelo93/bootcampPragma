package com.bootcamp.emazonapi.driving.mapper;

import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.driving.dto.request.AddMarcaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IMarcaRequestMapper {

    @Mapping(target = "id", ignore = true)
    Marca addRequestToMarca(AddMarcaRequest addMarcaRequest);

}