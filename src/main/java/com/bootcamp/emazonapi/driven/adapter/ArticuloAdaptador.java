package com.bootcamp.emazonapi.driven.adapter;

import com.bootcamp.emazonapi.config.Constants;
import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazonapi.driven.entity.ArticuloEntity;
import com.bootcamp.emazonapi.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazonapi.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazonapi.driven.exceptions.ProductAlreadyExistsException;
import com.bootcamp.emazonapi.driven.mapper.IArticuloEntityMapper;
import com.bootcamp.emazonapi.driven.repository.IArticuloRepository;
import com.bootcamp.emazonapi.driving.dto.response.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ArticuloAdaptador implements IArticuloPersistencePort {

    private final IArticuloRepository articuloRepository;
    private final IArticuloEntityMapper articuloEntityMapper;
    Logger logger = Logger.getLogger(getClass().getName());


    private Pageable getPageable(int page, int size, String order) {
        if ("desc".equalsIgnoreCase(order)) {
            return PageRequest.of(page, size, Sort.by("nombre").descending());
        } else if ("asc".equalsIgnoreCase(order)) {
            return PageRequest.of(page, size, Sort.by("nombre").ascending());
        } else {
            // Ordena por ID si el parámetro de orden está vacío o no es reconocido
            return PageRequest.of(page, size, Sort.by("id"));
        }
    }

    @Override
    public void guardarArticulo(Articulo articulo) {
        if (articuloRepository.findByNombre(articulo.getNombre()).isPresent()) {
            throw new ProductAlreadyExistsException(Constants.ARTICULO_YA_EXISTE_EXCEPCION_MENSAJE);
        }
        ArticuloEntity savedEntity = articuloRepository.save(articuloEntityMapper.articuloToArticuloEntity(articulo));
        logger.info("ID after save: " + savedEntity.getId());
    }


    @Override
    public Articulo obtenerArticulo(String nombre) {
        ArticuloEntity articulo = articuloRepository.findByNombreContaining(nombre).orElseThrow(ElementNotFoundException::new);
        return articuloEntityMapper.entityToArticulo(articulo);
    }

    
    @Override
    public PagedResponse<Articulo> listarArticulos(int page, int size, String order) {
        Pageable pageable = getPageable(page, size, order);
        Page<ArticuloEntity> articuloPage = articuloRepository.findAll(pageable);
        List<ArticuloEntity> articulos = articuloPage.getContent();

        if (articulos.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Mapea las entidades a objetos de dominio Articulo
        List<Articulo> articuloList = articuloEntityMapper.articuloToArticuloEntityList(articulos);

        return new PagedResponse<>(
                articuloList,
                articuloPage.getNumber(),
                articuloPage.getSize(),
                articuloPage.getTotalElements(),
                articuloPage.getTotalPages(),
                articuloPage.isLast()
        );

    }

    @Override
    public PagedResponse<Articulo> listarArticulosPorCategoria(String nombreCategoria, int page, int size, String order) {
        Pageable pageable = getPageable(page, size, order);
        Page<ArticuloEntity> articuloPage = articuloRepository.findByCategoriasNombreContaining(nombreCategoria, pageable);
        List<ArticuloEntity> articuloEntities = articuloPage.getContent();
        List<Articulo> articulos = articuloEntityMapper.articuloToArticuloEntityList(articuloEntities);

        return new PagedResponse<>(
                articulos,
                articuloPage.getNumber(),
                articuloPage.getSize(),
                articuloPage.getTotalElements(),
                articuloPage.getTotalPages(),
                articuloPage.isLast()
        );
    }

    @Override
    public PagedResponse<Articulo> listarArticulosPorMarca(String nombreMarca, int page, int size, String order) {
        Pageable pageable = getPageable(page, size, order);
        Page<ArticuloEntity> articuloPage = articuloRepository.findByMarcaNombreContaining(nombreMarca, pageable);
        List<ArticuloEntity> articuloEntities = articuloPage.getContent();
        List<Articulo> articulos = articuloEntityMapper.articuloToArticuloEntityList(articuloEntities);

        return new PagedResponse<>(
                articulos,
                articuloPage.getNumber(),
                articuloPage.getSize(),
                articuloPage.getTotalElements(),
                articuloPage.getTotalPages(),
                articuloPage.isLast()
        );
    }
}
