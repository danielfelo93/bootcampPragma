package com.bootcamp.emazon.transaccion_micro.driven.mapper;

import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.driven.entity.SuministroEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISuministroEntityMapper {

    Suministro toSuministro(SuministroEntity suministroEntity);

    SuministroEntity toEntity(Suministro suministro);
    List<Suministro> suministroEntityToSuministroList(List<SuministroEntity> suministroEntities);
}
