package com.bootcamp.emazon.stock_micro.driven.repository;


import com.bootcamp.emazon.stock_micro.driven.entity.ArticuloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long> {

    Optional<ArticuloEntity> findByNombreContaining(String nombre);
    Optional<ArticuloEntity> findByNombre(String nombre);
    Page<ArticuloEntity> findAll(Pageable pageable);

    Page<ArticuloEntity> findByCategoriasNombreContaining(String nombreCategoria, Pageable pageable);
    Page<ArticuloEntity> findByMarcaNombreContaining(String nombreMarca, Pageable pageable);

}
