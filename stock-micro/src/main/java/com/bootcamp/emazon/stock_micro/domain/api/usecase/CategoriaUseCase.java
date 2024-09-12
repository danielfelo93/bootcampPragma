package com.bootcamp.emazon.stock_micro.domain.api.usecase;


import com.bootcamp.emazon.stock_micro.domain.api.ICategoriaServicePort;
import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

public class CategoriaUseCase implements ICategoriaServicePort {

    private final ICategoriaPersistencePort categoriaPersistencePort;

    public CategoriaUseCase(ICategoriaPersistencePort categoriaPersistencePort) {
        this.categoriaPersistencePort = categoriaPersistencePort;
    }

    @Override
    public void guardarCategoria(Categoria categoria) {
        categoriaPersistencePort.guardarCategoria(categoria);
     }


    @Override
    public PagedResponse<Categoria> listarCategorias(int page, int size, String order){
        return categoriaPersistencePort.listarCategorias(page,size,order);
    }

}

