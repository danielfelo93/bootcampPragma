package com.bootcamp.emazonapi.driven.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.bootcamp.emazonapi.driven.entity.CategoriaEntity;


public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNombreContaining(String nombre);
    Optional<CategoriaEntity> findByNombre(String nombre);
    Page<CategoriaEntity> findAll(Pageable pageable);

}
