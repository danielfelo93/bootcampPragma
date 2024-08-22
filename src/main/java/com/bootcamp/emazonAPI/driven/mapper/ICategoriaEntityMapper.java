package com.bootcamp.emazonAPI.driven.mapper;

import java.util.List;

import org.mapstruct.Mapper;


import com.bootcamp.emazonAPI.domain.service.Categoria;
import com.bootcamp.emazonAPI.driven.entity.CategoriaEntity;

@Mapper(componentModel = "spring")
public interface ICategoriaEntityMapper {

    Categoria entityToCategoria(CategoriaEntity categoriaEntity);
    CategoriaEntity categoriaToCategoriaEntity(Categoria categoria);
    List<Categoria> categoriaToCategoriaEntityList(List<CategoriaEntity> categoriaEntities);
}
