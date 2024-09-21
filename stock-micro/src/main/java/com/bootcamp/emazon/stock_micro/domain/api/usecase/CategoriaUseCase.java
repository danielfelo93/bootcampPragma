package com.bootcamp.emazon.stock_micro.domain.api.usecase;


import com.bootcamp.emazon.stock_micro.domain.api.ICategoriaServicePort;
import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

public class CategoriaUseCase implements ICategoriaServicePort {

    private final ICategoriaPersistencePort categoriaPersistencePort;

    public CategoriaUseCase(ICategoriaPersistencePort categoriaPersistencePort) {
        this.categoriaPersistencePort = categoriaPersistencePort;
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new NullPointerException(ConstantesDominio.CATEGORIA_NULA_MENSAJE);
        }
        categoriaPersistencePort.guardarCategoria(categoria);
    }


    @Override
    public PagedResponse<Categoria> listarCategorias(int page, int size, String order){
        if (page < 0) {
            throw new IllegalArgumentException(ConstantesDominio.CAMPO_PAGINA_NEGATIVE_MENSAJE);
        }
        if (size <= 0) {
            throw new IllegalArgumentException(ConstantesDominio.TAMANO_NEGATIVO_MENSAJE);
        }
        return categoriaPersistencePort.listarCategorias(page,size,order);
    }

}

