package com.bootcamp.emazonAPI.driven.adapter;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bootcamp.emazonAPI.domain.service.Categoria;
import com.bootcamp.emazonAPI.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonAPI.driven.entity.CategoriaEntity;
import com.bootcamp.emazonAPI.driven.exceptions.ElementNotFoundException;
import com.bootcamp.emazonAPI.driven.exceptions.NoDataFoundException;
import com.bootcamp.emazonAPI.driven.exceptions.ProductAlreadyExistsException;
import com.bootcamp.emazonAPI.driven.mapper.ICategoriaEntityMapper;
import com.bootcamp.emazonAPI.driven.repository.ICategoriaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoriaAdaptador implements ICategoriaPersistencePort {

    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;


    @Override
    public void guardarCategoria(Categoria categoria) {
        if (categoriaRepository.findByNombre(categoria.getNombre()).isPresent()) {
            throw new ProductAlreadyExistsException();
        }
        CategoriaEntity savedEntity = categoriaRepository.save(categoriaEntityMapper.categoriaToCategoriaEntity(categoria));
        System.out.println("ID after save: " + savedEntity.getId());
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
