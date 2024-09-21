package com.bootcamp.emazon.stock_micro.domain.api.usecase;



import com.bootcamp.emazon.stock_micro.domain.api.IMarcaServicePort;
import com.bootcamp.emazon.stock_micro.domain.service.ConstantesDominio;
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
        if (marca == null) {
            throw new NullPointerException(ConstantesDominio.MARCA_NULA_MENSAJE);
        }
        marcaPersistencePort.guardarMarca(marca);
     }


    @Override
    public Marca obtenerMarca(String nombre) {
        return marcaPersistencePort.obtenerMarca(nombre);
    }

    @Override
    public PagedResponse<Marca> listarMarcas(int page, int size, String order){
        if (page < 0) {
            throw new IllegalArgumentException(ConstantesDominio.CAMPO_PAGINA_NEGATIVE_MENSAJE);
        }
        if (size <= 0) {
            throw new IllegalArgumentException(ConstantesDominio.TAMANO_NEGATIVO_MENSAJE);
        }
        return marcaPersistencePort.listarMarcas(page,size,order);
    }

}

