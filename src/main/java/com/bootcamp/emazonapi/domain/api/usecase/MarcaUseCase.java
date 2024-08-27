package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.domain.api.IMarcaServicePort;
import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.domain.spi.IMarcaPersistencePort;

import java.util.List;

public class MarcaUseCase implements IMarcaServicePort {

    private IMarcaPersistencePort marcaPersistencePort;

    public MarcaUseCase(IMarcaPersistencePort marcaPersistencePort) {
        this.marcaPersistencePort = marcaPersistencePort;
    }

    @Override
    public void guardarMarca(Marca marca) {
        marcaPersistencePort.guardarMarca(marca);
     }


    @Override
    public Marca obtenerMarca(String nombre) {
        return marcaPersistencePort.obtenerMarca(nombre);
    }

    @Override
    public List<Marca> listarMarcas(int page, int size, String order){
        return marcaPersistencePort.listarMarcas(page,size,order);
    }

}

