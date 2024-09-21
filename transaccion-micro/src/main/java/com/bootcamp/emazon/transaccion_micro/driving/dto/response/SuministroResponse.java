package com.bootcamp.emazon.transaccion_micro.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class SuministroResponse {

    private Long articuloId;

    private Integer cantidad;

    private LocalDateTime fechaDeIngreso;

}
