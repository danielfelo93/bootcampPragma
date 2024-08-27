package com.bootcamp.emazonapi.driven.mapper;

import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.driven.entity.MarcaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarcaEntityMapper {

    Marca entityToMarca(MarcaEntity marcaEntity);
    MarcaEntity marcaToMarcaEntity(Marca marca);
    List<Marca> marcaToMarcaEntityList(List<MarcaEntity> marcaEntities);
}
