package com.bootcamp.emazonapi.driving.mapper;

import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.driving.dto.response.MarcaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarcaResponseMapper {

    //@Mapping(target = "id", ignore = true)
    MarcaResponse marcaToResponse(Marca marca);
    List<MarcaResponse> marcaToResponseList(List<Marca> marcas);

}

