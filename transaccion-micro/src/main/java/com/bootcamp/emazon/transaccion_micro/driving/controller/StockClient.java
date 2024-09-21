package com.bootcamp.emazon.transaccion_micro.driving.controller;

import com.bootcamp.emazon.transaccion_micro.config.FeignConfig;
import com.bootcamp.emazon.transaccion_micro.driving.dto.request.SuministroRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock-micro", url = "${stock.service.url}", configuration = FeignConfig.class)
public interface StockClient {

    @PutMapping("/articulos/aux_bodega")
    void actualizarStock(@RequestBody SuministroRequest request);
}
