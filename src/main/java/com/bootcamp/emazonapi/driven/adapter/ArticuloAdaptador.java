package com.bootcamp.emazonapi.driven.adapter;

import com.bootcamp.emazonapi.config.Constants;
import com.bootcamp.emazonapi.domain.service.Articulo;
import com.bootcamp.emazonapi.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazonapi.driven.entity.ArticuloEntity;
import com.bootcamp.emazonapi.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazonapi.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazonapi.driven.exceptions.ProductAlreadyExistsException;
import com.bootcamp.emazonapi.driven.mapper.IArticuloEntityMapper;
import com.bootcamp.emazonapi.driven.repository.IArticuloRepository;
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
    public List<Articulo> listarArticulos(int page, int size, String order) {
        Pageable pageable;

        // Configura el Pageable basado en el parámetro de orden
        if ("desc".equalsIgnoreCase(order)) {
            pageable = PageRequest.of(page, size, Sort.by("nombre").descending());
        } else if ("asc".equalsIgnoreCase(order)) {
            pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());
        } else {
            // Ordena por ID si el parámetro de orden está vacío o no es reconocido
            pageable = PageRequest.of(page, size, Sort.by("id"));
        }

        // Obtén las categorías usando el Pageable configurado
        Page<ArticuloEntity> articuloPage = articuloRepository.findAll(pageable);
        List<ArticuloEntity> articulos = articuloPage.getContent();

        // Lanza excepción si no hay datos
        if (articulos.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Mapea las entidades a objetos de dominio Articulo
        return articuloEntityMapper.articuloToArticuloEntityList(articulos);
    }

}
