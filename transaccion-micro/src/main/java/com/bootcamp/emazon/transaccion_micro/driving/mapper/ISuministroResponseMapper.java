package com.bootcamp.emazon.transaccion_micro.driving.mapper;

import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.SuministroResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISuministroResponseMapper {

    SuministroResponse suministroToResponse(Suministro suministro);
    PagedResponse<SuministroResponse> suministroToResponseList(PagedResponse<Suministro> suministros);

}

