package com.bootcamp.emazon.stock_micro.driven.mapper;

import com.bootcamp.emazon.stock_micro.domain.service.Articulo;
import com.bootcamp.emazon.stock_micro.driven.entity.ArticuloEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticuloEntityMapper {

    @Mapping(source = "marca.id", target = "marca.id")
    @Mapping(source = "categorias", target = "categorias")
    Articulo entityToArticulo(ArticuloEntity articuloEntity);

    @Mapping(source = "marca.id", target = "marca.id")
    @Mapping(source = "categorias", target = "categorias")
    ArticuloEntity articuloToArticuloEntity(Articulo articulo);
    List<Articulo> articuloToArticuloEntityList(List<ArticuloEntity> articuloEntities);
}
