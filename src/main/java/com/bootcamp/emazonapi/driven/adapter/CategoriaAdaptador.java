package com.bootcamp.emazonapi.driven.adapter;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
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
import org.springframework.data.domain.Sort;

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
    public List<Categoria> listarCategorias(int page, int size, String order) {
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
        Page<CategoriaEntity> categoriaPage = categoriaRepository.findAll(pageable);
        List<CategoriaEntity> categorias = categoriaPage.getContent();

        // Lanza excepción si no hay datos
        if (categorias.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Mapea las entidades a objetos de dominio Categoria
        return categoriaEntityMapper.categoriaToCategoriaEntityList(categorias);
    }

}
