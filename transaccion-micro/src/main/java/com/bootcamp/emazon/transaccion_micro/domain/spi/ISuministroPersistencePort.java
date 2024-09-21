package com.bootcamp.emazon.transaccion_micro.domain.spi;

import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;
import java.util.Optional;

public interface ISuministroPersistencePort {

    Suministro guardarSuministro(Suministro suministro);

    Optional<Suministro> obtenerSuministroPorArticuloId(Long articuloId);

    PagedResponse<Suministro> obtenerTodosSuministros(int page, int size, String order);

}

