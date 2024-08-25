package com.bootcamp.emazonapi.domain.api.usecase;

import java.util.List;

import com.bootcamp.emazonapi.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;

public class CategoriaUseCase implements ICategoriaServicePort {

    private ICategoriaPersistencePort categoriaPersistencePort;

    public CategoriaUseCase(ICategoriaPersistencePort categoriaPersistencePort) {
        this.categoriaPersistencePort = categoriaPersistencePort;
    }

    @Override
    public void guardarCategoria(Categoria categoria) {  
        categoriaPersistencePort.guardarCategoria(categoria);
     }


    @Override
    public Categoria obtenerCategoria(String nombre) { 
        return categoriaPersistencePort.obtenerCategoria(nombre); 
    }

    @Override
    public List<Categoria> listarCategorias(int page, int size, String order){
        return categoriaPersistencePort.listarCategorias(page,size,order);
    }

}

