package com.bootcamp.emazon.stock_micro.driving.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddCategoriaRequest {

    private String nombre;

    private String descripcion;

}