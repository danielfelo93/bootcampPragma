package com.bootcamp.emazon.stock_micro.driven.mapper;

import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.driven.entity.MarcaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarcaEntityMapper {

    Marca entityToMarca(MarcaEntity marcaEntity);
    MarcaEntity marcaToMarcaEntity(Marca marca);
    List<Marca> marcaToMarcaEntityList(List<MarcaEntity> marcaEntities);
}
