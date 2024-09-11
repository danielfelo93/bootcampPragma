package com.bootcamp.emazonapi.driving.mapper;

import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.driving.dto.response.ArticuloMarcaResponse;
import com.bootcamp.emazonapi.driving.dto.response.MarcaResponse;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarcaResponseMapper {

    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "descripcion", ignore = true)
    MarcaResponse marcaToResponse(Marca marca);
    PagedResponse<MarcaResponse> marcaToResponseList(PagedResponse<Marca> marcas);

    ArticuloMarcaResponse articuloMarcaToResponse(Marca marca);


}

