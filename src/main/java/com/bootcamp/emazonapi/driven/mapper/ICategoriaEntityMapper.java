package com.bootcamp.emazonapi.driven.mapper;

import java.util.List;

import org.mapstruct.Mapper;


import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.driven.entity.CategoriaEntity;

@Mapper(componentModel = "spring")
public interface ICategoriaEntityMapper {

    Categoria entityToCategoria(CategoriaEntity categoriaEntity);
    CategoriaEntity categoriaToCategoriaEntity(Categoria categoria);
    List<Categoria> categoriaToCategoriaEntityList(List<CategoriaEntity> categoriaEntities);
}
