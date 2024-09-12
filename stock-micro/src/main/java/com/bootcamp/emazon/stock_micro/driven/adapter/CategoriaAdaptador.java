package com.bootcamp.emazon.stock_micro.driven.adapter;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.bootcamp.emazon.stock_micro.config.Constants;
import com.bootcamp.emazon.stock_micro.domain.service.Categoria;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.driven.entity.CategoriaEntity;
import com.bootcamp.emazon.stock_micro.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazon.stock_micro.driven.exceptions.ProductAlreadyExistsException;
import com.bootcamp.emazon.stock_micro.driven.mapper.ICategoriaEntityMapper;
import com.bootcamp.emazon.stock_micro.driven.repository.ICategoriaRepository;
import com.bootcamp.emazon.stock_micro.driving.dto.response.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class CategoriaAdaptador implements ICategoriaPersistencePort {

    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;
    Logger logger = Logger.getLogger(getClass().getName());


    @Override
    public void guardarCategoria(Categoria categoria) {
        if (categoriaRepository.findByNombre(categoria.getNombre()).isPresent()) {
            throw new ProductAlreadyExistsException(Constants.CATEGORIA_YA_EXISTE_EXCEPCION_MENSAJE);
        }
        CategoriaEntity savedEntity = categoriaRepository.save(categoriaEntityMapper.categoriaToCategoriaEntity(categoria));
        logger.info("ID after save: " + savedEntity.getId());
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findById(id);
        return categoriaOptional.map(categoriaEntityMapper::entityToCategoria);
    }

    @Override
    public PagedResponse<Categoria> listarCategorias(int page, int size, String order) {
        Pageable pageable;

        if ("desc".equalsIgnoreCase(order)) {
            pageable = PageRequest.of(page, size, Sort.by("nombre").descending());
        } else if ("asc".equalsIgnoreCase(order)) {
            pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by("id"));
        }

        Page<CategoriaEntity> categoriaPage = categoriaRepository.findAll(pageable);
        List<CategoriaEntity> categorias = categoriaPage.getContent();

        if (categorias.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<Categoria> categoriaList = categoriaEntityMapper.categoriaToCategoriaEntityList(categorias);

        return new PagedResponse<>(
                categoriaList,
                categoriaPage.getNumber(),
                categoriaPage.getSize(),
                categoriaPage.getTotalElements(),
                categoriaPage.getTotalPages(),
                categoriaPage.isLast()
        );
    }

}
