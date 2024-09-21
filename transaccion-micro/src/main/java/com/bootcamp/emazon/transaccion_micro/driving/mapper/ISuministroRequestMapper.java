package com.bootcamp.emazon.transaccion_micro.driving.mapper;

import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.driving.dto.request.SuministroRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ISuministroRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaDeIngreso", ignore = true)
    Suministro requestToSuministro(SuministroRequest suministroRequest);

}