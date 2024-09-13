package com.bootcamp.emazon.stock_micro.domain.api.usecase;


import com.bootcamp.emazon.stock_micro.domain.api.ICategoriaServicePort;
import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.driving.controller.UserClient;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

public class CategoriaUseCase implements ICategoriaServicePort {

    private final ICategoriaPersistencePort categoriaPersistencePort;
    private final UserClient userClient;

    public CategoriaUseCase(ICategoriaPersistencePort categoriaPersistencePort, UserClient userClient) {
        this.categoriaPersistencePort = categoriaPersistencePort;
        this.userClient = userClient;
    }

    @Override
    public void guardarCategoria(String token, Categoria categoria) {

        try {
            userClient.validateToken(token);
            categoriaPersistencePort.guardarCategoria(categoria);
        } catch (Exception e) {
            // Manejo de errores apropiado
            throw new RuntimeException("Token no válido o error al guardar la categoría");
        }
     }


    @Override
    public PagedResponse<Categoria> listarCategorias(int page, int size, String order){
        return categoriaPersistencePort.listarCategorias(page,size,order);
    }

}

