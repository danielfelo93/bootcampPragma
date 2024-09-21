package com.bootcamp.emazon.stock_micro.domain.api.usecase;

import com.bootcamp.emazon.stock_micro.domain.api.IArticuloServicePort;
import com.bootcamp.emazon.stock_micro.domain.exception.*;
import com.bootcamp.emazon.stock_micro.domain.service.Articulo;
import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void crearArticulo(Articulo articulo, Long marcaId, List<Long> categoriaIds) {
        // Validar nombre vacío
        if (articulo.getNombre() == null || articulo.getNombre().isEmpty()) {
            throw new EmptyFieldException(ConstantesDominio.CAMPO_NOMBRE_NULL_MENSAJE);
        }

        // Validar descripción vacía
        if (articulo.getDescripcion() == null || articulo.getDescripcion().isEmpty()) {
            throw new EmptyFieldException(ConstantesDominio.CAMPO_DESCRIPCION_NULL_MENSAJE);
        }

        // Validar y obtener la marca
        Marca marca = marcaPersistencePort.obtenerMarcaPorId(marcaId)
                .orElseThrow(() -> new NoSuchElementException(ConstantesDominio.MARCA_NO_ENCONTRADA_MENSAJE));

        // Validar y obtener las categorías
        Set<Categoria> categorias = categoriaIds.stream()
                .map(categoriaId -> categoriaPersistencePort.obtenerCategoriaPorId(categoriaId)
                        .orElseThrow(() -> new NoSuchElementException(ConstantesDominio.CATEGORIA_NO_ENCONTRADA_MENSAJE + categoriaId)))
                .collect(Collectors.toSet());

        // Validar tamaño de categorías
        if (categorias.isEmpty() || categorias.size() > ConstantesDominio.MAX_CATEGORIAS_TAMANO) {
            throw new LimitExceededException(ConstantesDominio.CAMPO_CATEGORIA_TAMANO_EXCEDIDO_MENSAJE);
        }

        // Validar duplicados en los IDs de categorías
        Set<Long> uniqueCategoriaIds = new HashSet<>(categoriaIds);
        if (uniqueCategoriaIds.size() != categoriaIds.size()) {
            throw new DuplicatedFieldException(ConstantesDominio.CAMPO_CATEGORIA_DUPLICADA_MENSAJE);
        }

        // Asignar marca y categorías al artículo
        articulo.setMarca(marca);
        articulo.setCategorias(categorias);
        articulo.setCantidad(0);  // Inicializar la cantidad

        // Guardar el artículo
        articuloPersistencePort.crearArticulo(articulo);
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

    @Override
    public void agregarArticulos(long articuloId, int cantidad) {
        // Obtener el artículo existente
        Articulo articulo = articuloPersistencePort.obtenerArticuloPorId(articuloId)
                .orElseThrow(() -> new NoSuchElementException(ConstantesDominio.ARTICULO_NO_ENCONTRADO));

        // Incrementar la cantidad del artículo
        int nuevaCantidad = articulo.getCantidad() + cantidad;
        articulo.setCantidad(nuevaCantidad);

        // Guardar el artículo actualizado
        articuloPersistencePort.agregarArticulos(articulo);
    }

}

