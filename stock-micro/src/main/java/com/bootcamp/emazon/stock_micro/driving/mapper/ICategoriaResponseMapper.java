package com.bootcamp.emazon.stock_micro.driving.mapper;

import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.driving.dto.response.ArticuloCategoriaResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.CategoriaResponse;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoriaResponseMapper {

    //@Mapping(target = "id", ignore = true)
    CategoriaResponse categoriaToResponse(Categoria categoria);
    PagedResponse<CategoriaResponse> categoriaToResponseList(PagedResponse<Categoria> categorias);

    ArticuloCategoriaResponse articuloCategoriaToResponse(Categoria categoria);
}

