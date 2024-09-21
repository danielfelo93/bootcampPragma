package com.bootcamp.emazon.transaccion_micro.driving.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SuministroRequest {

    @NotNull(message = "El id del artículo es obligatorio")
    private Long articuloId;

    @Min(value = 1, message = "La cantidad mínima es 1")
    private Integer cantidad;

}