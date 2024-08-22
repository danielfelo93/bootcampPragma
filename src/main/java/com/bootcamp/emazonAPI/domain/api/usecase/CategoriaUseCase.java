package com.bootcamp.emazonAPI.domain.api.usecase;

import java.util.List;

import com.bootcamp.emazonAPI.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonAPI.domain.service.Categoria;
import com.bootcamp.emazonAPI.domain.spi.ICategoriaPersistencePort;

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
    public List<Categoria> listarCategorias(int page, int size){
        return categoriaPersistencePort.listarCategorias(page,size);
    }

    @Override
    public List<Categoria> listarCategoriasfree() {
        return categoriaPersistencePort.listarCategoriasfree();
    }

}

