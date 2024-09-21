package com.bootcamp.emazon.transaccion_micro.domain.api.usecase;

import com.bootcamp.emazon.transaccion_micro.domain.exception.EmptyFieldException;
import com.bootcamp.emazon.transaccion_micro.domain.exception.StockUpdateException;
import com.bootcamp.emazon.transaccion_micro.domain.api.ISuministroServicePort;
import com.bootcamp.emazon.transaccion_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.transaccion_micro.domain.service.Suministro;
import com.bootcamp.emazon.transaccion_micro.domain.spi.ISuministroPersistencePort;
import com.bootcamp.emazon.transaccion_micro.driving.controller.StockClient;
import com.bootcamp.emazon.transaccion_micro.driving.dto.request.SuministroRequest;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;

import java.time.LocalDateTime;
import java.util.Optional;


public class SuministroService implements ISuministroServicePort {

    private final ISuministroPersistencePort suministroPersistencePort;
    private final StockClient stockClient;

    public SuministroService(ISuministroPersistencePort suministroPersistencePort, StockClient stockClient) {
        this.suministroPersistencePort = suministroPersistencePort;
        this.stockClient = stockClient;
    }

    @Override
    public Suministro guardarSuministro(Suministro suministro) {
        // Intentar actualizar el stock del artículo
        try {
            stockClient.actualizarStock(
                    new SuministroRequest(suministro.getArticuloId(), suministro.getCantidad()));
        } catch (Exception e) {
            throw new StockUpdateException(ConstantesDominio.ERROR_EN_STOCK);
        }

        // Verificar si ya existe un suministro para el mismo artículo
        Optional<Suministro> suministroExistente = suministroPersistencePort.obtenerSuministroPorArticuloId(suministro.getArticuloId());
        Suministro suministroActualizado;

        if (suministroExistente.isPresent()) {
            suministroActualizado = suministroExistente.get();
            int cantidadActual = suministroActualizado.getCantidad();
            int cantidadNueva = suministro.getCantidad();

            suministroActualizado.setCantidad(cantidadActual + cantidadNueva);
            suministroActualizado.setFechaDeIngreso(LocalDateTime.now());
        } else {
            // Si no existe suministro, agregar uno nuevo
            suministroActualizado = suministro;
        }
        return suministroPersistencePort.guardarSuministro(suministroActualizado);
    }

    @Override
    public Optional<Suministro> obtenerSuministroPorArticuloId(Long articuloId) {
        // Validar que el ID del artículo no sea nulo
        if (articuloId == null) {
            throw new EmptyFieldException(ConstantesDominio.CAMPO_ID_VACIO);
        }

        return suministroPersistencePort.obtenerSuministroPorArticuloId(articuloId);
    }

    @Override
    public PagedResponse<Suministro> obtenerTodosSuministros(int page, int size, String order) {
        return suministroPersistencePort.obtenerTodosSuministros(page,size,order);
    }

}

