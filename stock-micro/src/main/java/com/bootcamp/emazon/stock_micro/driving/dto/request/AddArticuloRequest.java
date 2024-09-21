package com.bootcamp.emazon.stock_micro.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddArticuloRequest {

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private Long marcaId;

    private List<Long> categoriaIds;


}