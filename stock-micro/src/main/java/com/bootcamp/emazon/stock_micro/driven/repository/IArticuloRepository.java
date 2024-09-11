package com.bootcamp.emazonapi.driven.repository;

import com.bootcamp.emazonapi.driven.entity.ArticuloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long> {

    Optional<ArticuloEntity> findByNombreContaining(String nombre);
    Optional<ArticuloEntity> findByNombre(String nombre);
    Page<ArticuloEntity> findAll(Pageable pageable);

    Page<ArticuloEntity> findByCategoriasNombreContaining(String nombreCategoria, Pageable pageable);
    Page<ArticuloEntity> findByMarcaNombreContaining(String nombreMarca, Pageable pageable);

}
