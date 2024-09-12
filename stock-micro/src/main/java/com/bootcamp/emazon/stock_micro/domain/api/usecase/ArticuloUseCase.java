package com.bootcamp.emazon.stock_micro.domain.api.usecase;

import com.bootcamp.emazon.stock_micro.domain.api.IArticuloServicePort;
import com.bootcamp.emazon.stock_micro.domain.exception.DuplicatedFieldException;
import com.bootcamp.emazon.stock_micro.domain.exception.LimitExceededException;
import com.bootcamp.emazon.stock_micro.domain.service.Articulo;
import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.domain.service.ConstantesDominio;
import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import org.springframework.web.client.RestTemplate;

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
    public void guardarArticulo(Articulo articulo, Long marcaId, List<Long> categoriaIds) {
        // Validar y obtener la marca
        Marca marca = marcaPersistencePort.obtenerMarcaPorId(marcaId)
                .orElseThrow(NoSuchElementException::new);

        // Validar y obtener las categorías
        Set<Categoria> categorias = categoriaIds.stream()
                .map(categoriaId -> categoriaPersistencePort.obtenerCategoriaPorId(categoriaId)
                        .orElseThrow(NoSuchElementException::new))
                .collect(Collectors.toSet());

        // Guardar Artículo
        articulo.setMarca(marca);
        articulo.setCategorias(categorias);

        if (categorias.isEmpty() || categorias.size() > ConstantesDominio.MAX_CATEGORIAS_TAMANO) {
            throw new LimitExceededException(ConstantesDominio.CAMPO_CATEGORIA_TAMANO_EXCEDIDO_MENSAJE);
        }

        // Validar duplicados en los IDs de categorías
        Set<Long> uniqueCategoriaIds = new HashSet<>(categoriaIds);
        if (uniqueCategoriaIds.size() != categoriaIds.size()) {
            throw new DuplicatedFieldException(ConstantesDominio.CAMPO_CATEGORIA_DUPLICADA_MENSAJE);
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

