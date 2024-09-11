package com.bootcamp.emazonapi.driving.mapper;

import java.util.List;

import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.driving.dto.response.ArticuloCategoriaResponse;
import com.bootcamp.emazonapi.driving.dto.response.ArticuloMarcaResponse;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
import org.mapstruct.Mapper;


import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.driving.dto.response.CategoriaResponse;

@Mapper(componentModel = "spring")
public interface ICategoriaResponseMapper {

    //@Mapping(target = "id", ignore = true)
    CategoriaResponse categoriaToResponse(Categoria categoria);
    PagedResponse<CategoriaResponse> categoriaToResponseList(PagedResponse<Categoria> categorias);

    ArticuloCategoriaResponse articuloCategoriaToResponse(Categoria categoria);
}

