package com.bootcamp.emazon.stock_micro.driven.adapter;

import com.bootcamp.emazon.stock_micro.config.Constants;
import com.bootcamp.emazon.stock_micro.domain.service.Marca;
import com.bootcamp.emazon.stock_micro.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazon.stock_micro.driven.entity.MarcaEntity;
import com.bootcamp.emazon.stock_micro.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazon.stock_micro.driven.exceptions.ProductAlreadyExistsException;
import com.bootcamp.emazon.stock_micro.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazon.stock_micro.driven.mapper.IMarcaEntityMapper;
import com.bootcamp.emazon.stock_micro.driven.repository.IMarcaRepository;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class MarcaAdaptador implements IMarcaPersistencePort {

    private final IMarcaRepository marcaRepository;
    private final IMarcaEntityMapper marcaEntityMapper;
    Logger logger = Logger.getLogger(getClass().getName());


    @Override
    public void guardarMarca(Marca marca) {
        if (marcaRepository.findByNombre(marca.getNombre()).isPresent()) {
            throw new ProductAlreadyExistsException(Constants.MARCA_YA_EXISTE_EXCEPCION_MENSAJE);
        }
        MarcaEntity savedEntity = marcaRepository.save(marcaEntityMapper.marcaToMarcaEntity(marca));
        logger.info("ID after save: " + savedEntity.getId());
    }


    @Override
    public Marca obtenerMarca(String nombre) {
        MarcaEntity marca = marcaRepository.findByNombreContaining(nombre).orElseThrow(ElementNotFoundException::new);
        return marcaEntityMapper.entityToMarca(marca);
    }

    @Override
    public Optional<Marca> obtenerMarcaPorId(Long id) {
        Optional<MarcaEntity> marcaOptional = marcaRepository.findById(id);
        return marcaOptional.map(marcaEntityMapper::entityToMarca);
    }
    
    @Override
    public PagedResponse<Marca> listarMarcas(int page, int size, String order) {
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
        Page<MarcaEntity> marcaPage = marcaRepository.findAll(pageable);
        List<MarcaEntity> marcas = marcaPage.getContent();

        // Lanza excepción si no hay datos
        if (marcas.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Mapea las entidades a objetos de dominio Marca
        List<Marca> marcaList = marcaEntityMapper.marcaToMarcaEntityList(marcas);

        // Construye el objeto PagedResponse con los metadatos de paginación
        return new PagedResponse<>(
                marcaList,
                marcaPage.getNumber(),
                marcaPage.getSize(),
                marcaPage.getTotalElements(),
                marcaPage.getTotalPages(),
                marcaPage.isLast()
        );
    }

}
