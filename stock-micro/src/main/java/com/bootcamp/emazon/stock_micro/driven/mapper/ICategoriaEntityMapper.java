package com.bootcamp.emazon.stock_micro.driven.mapper;

import java.util.List;

import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.driven.entity.CategoriaEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ICategoriaEntityMapper {

    Categoria entityToCategoria(CategoriaEntity categoriaEntity);
    CategoriaEntity categoriaToCategoriaEntity(Categoria categoria);
    List<Categoria> categoriaToCategoriaEntityList(List<CategoriaEntity> categoriaEntities);
}
