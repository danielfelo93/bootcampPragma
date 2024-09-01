package com.bootcamp.emazonapi.domain.api.usecase;

import com.bootcamp.emazonapi.domain.api.IArticuloServicePort;
import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.service.Marca;
import com.bootcamp.emazonapi.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticuloUseCase implements IArticuloServicePort {

    private final IArticuloPersistencePort articuloPersistencePort;
    private final ICategoriaPersistencePort categoriaPersistencePort;
    private final IMarcaPersistencePort marcaPersistencePort;


    public ArticuloUseCase(IArticuloPersistencePort articuloPersistencePort,
                           ICategoriaPersistencePort categoriaPersistencePort,
                           IMarcaPersistencePort marcaPersistencePort) {
        this.articuloPersistencePort = articuloPersistencePort;
        this.categoriaPersistencePort = categoriaPersistencePort;
        this.marcaPersistencePort = marcaPersistencePort;
    }

    @Override
    public void guardarArticulo(Articulo articulo, Long marcaId, List<Long> categoriaIds) {
        // Validar y obtener la marca
        Marca marca = marcaPersistencePort.obtenerMarcaPorId(marcaId)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        // Validar y obtener las categorías
        Set<Categoria> categorias = categoriaIds.stream()
                .map(categoriaId -> categoriaPersistencePort.obtenerCategoriaPorId(categoriaId)
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada")))
                .collect(Collectors.toSet());

        // Guardar Artículo
        articulo.setMarca(marca);
        articulo.setCategorias(categorias);

        if (categorias.isEmpty() || categorias.size() > 3) {
            throw new IllegalArgumentException("El artículo debe tener entre 1 y 3 categorías.");
        }

        // Validar duplicados en los IDs de categorías
        Set<Long> uniqueCategoriaIds = new HashSet<>(categoriaIds);
        if (uniqueCategoriaIds.size() != categoriaIds.size()) {
            throw new IllegalArgumentException("No se permiten categorías duplicadas.");
        }

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

    @Override
    public PagedResponse<Articulo> listarArticulosPorCategoria(String nombreCategoria, int page, int size, String order) {
        return articuloPersistencePort.listarArticulosPorCategoria(nombreCategoria, page, size, order);
    }

    @Override
    public PagedResponse<Articulo> listarArticulosPorMarca(String nombreMarca, int page, int size, String order) {
        return articuloPersistencePort.listarArticulosPorMarca(nombreMarca, page, size, order);
    }

}

