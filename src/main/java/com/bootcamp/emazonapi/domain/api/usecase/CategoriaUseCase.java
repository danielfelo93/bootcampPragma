package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

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

