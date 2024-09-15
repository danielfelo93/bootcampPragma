package com.bootcamp.emazon.transaccion_micro.domain.api.usecase;


import com.bootcamp.emazon.transaccion_micro.domain.api.IArticuloServicePort;
import com.bootcamp.emazon.transaccion_micro.domain.service.Articulo;
import com.bootcamp.emazon.transaccion_micro.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazon.transaccion_micro.driving.dto.response.PagedResponse;

public class ArticuloUseCase implements IArticuloServicePort {

    private final IArticuloPersistencePort articuloPersistencePort;

    public ArticuloUseCase(IArticuloPersistencePort articuloPersistencePort) {
        this.articuloPersistencePort = articuloPersistencePort;
    }

    @Override
    public void guardarArticulo(Articulo articulo) {
        articuloPersistencePort.guardarArticulo(articulo);
     }

    @Override
    public Articulo obtenerArticulo(String nombre) {
        return articuloPersistencePort.obtenerArticulo(nombre);
    }

    @Override
    public PagedResponse<Articulo> listarArticulos(int page, int size, String order){
        return articuloPersistencePort.listarArticulos(page,size,order);
    }

}

