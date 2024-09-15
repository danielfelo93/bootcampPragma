package com.bootcamp.emazon.transaccion_micro.driven.mapper;


import com.bootcamp.emazon.transaccion_micro.domain.service.Articulo;
import com.bootcamp.emazon.transaccion_micro.driven.entity.ArticuloEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IArticuloEntityMapper {

    Articulo entityToArticulo(ArticuloEntity articuloEntity);

    ArticuloEntity articuloToArticuloEntity(Articulo articulo);
    List<Articulo> articuloToArticuloEntityList(List<ArticuloEntity> articuloEntities);
}
