package com.bootcamp.emazon.stock_micro.driven.repository;

import java.util.Optional;

import com.bootcamp.emazon.stock_micro.driven.entity.CategoriaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNombreContaining(String nombre);
    Optional<CategoriaEntity> findByNombre(String nombre);
    Page<CategoriaEntity> findAll(Pageable pageable);


}
