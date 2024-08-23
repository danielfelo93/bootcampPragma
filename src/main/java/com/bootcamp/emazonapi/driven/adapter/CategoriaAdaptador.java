package com.bootcamp.emazonapi.driven.adapter;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bootcamp.emazonapi.domain.service.Categoria;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.driven.entity.CategoriaEntity;
import com.bootcamp.emazonapi.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazonapi.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazonapi.driven.exceptions.ProductAlreadyExistsException;
import com.bootcamp.emazonapi.driven.mapper.ICategoriaEntityMapper;
import com.bootcamp.emazonapi.driven.repository.ICategoriaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoriaAdaptador implements ICategoriaPersistencePort {

    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;
    Logger logger = Logger.getLogger(getClass().getName());


    @Override
    public void guardarCategoria(Categoria categoria) {
        if (categoriaRepository.findByNombre(categoria.getNombre()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        CategoriaEntity savedEntity = categoriaRepository.save(categoriaEntityMapper.categoriaToCategoriaEntity(categoria));
        logger.info("ID after save: " + savedEntity.getId());
    }


    @Override
    public Categoria obtenerCategoria(String nombre) {
        CategoriaEntity categoria = categoriaRepository.findByNombreContaining(nombre).orElseThrow(ElementNotFoundException::new);
        return categoriaEntityMapper.entityToCategoria(categoria);
    }

    
    @Override
    public List<Categoria> listarCategorias(int page, int size) {
        Pageable pagination = PageRequest.of(page, size);
        List<CategoriaEntity> categorias = categoriaRepository.findAll(pagination).getContent();
        if (categorias.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoriaEntityMapper.categoriaToCategoriaEntityList(categorias);
    }

    @Override
    public List<Categoria> listarCategoriasfree() {
        List<CategoriaEntity> categorias = categoriaRepository.findAll();
        return categoriaEntityMapper.categoriaToCategoriaEntityList(categorias);
    }

}
