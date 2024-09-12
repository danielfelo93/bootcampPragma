package com.bootcamp.emazon.stock_micro.driving.mapper;

import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.driving.dto.response.ArticuloMarcaResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.MarcaResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMarcaResponseMapper {

    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "descripcion", ignore = true)
    MarcaResponse marcaToResponse(Marca marca);
    PagedResponse<MarcaResponse> marcaToResponseList(PagedResponse<Marca> marcas);

    ArticuloMarcaResponse articuloMarcaToResponse(Marca marca);


}

