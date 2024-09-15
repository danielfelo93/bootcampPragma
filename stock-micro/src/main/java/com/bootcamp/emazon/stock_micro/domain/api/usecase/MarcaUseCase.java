package com.bootcamp.emazon.stock_micro.domain.api.usecase;



import com.bootcamp.emazon.stock_micro.domain.api.IMarcaServicePort;
import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

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
    public PagedResponse<Marca> listarMarcas(int page, int size, String order){
        return marcaPersistencePort.listarMarcas(page,size,order);
    }

}

