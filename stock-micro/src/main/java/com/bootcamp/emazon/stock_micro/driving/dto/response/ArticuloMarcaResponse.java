package com.bootcamp.emazon.stock_micro.driving.dto.response;



import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
public class ArticuloMarcaResponse {

    private final long id;

    private final String nombre;

}
