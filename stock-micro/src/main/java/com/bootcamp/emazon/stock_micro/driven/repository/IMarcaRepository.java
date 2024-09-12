package com.bootcamp.emazon.stock_micro.driven.repository;


import com.bootcamp.emazon.stock_micro.driven.entity.MarcaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IMarcaRepository extends JpaRepository<MarcaEntity, Long> {

    Optional<MarcaEntity> findByNombreContaining(String nombre);
    Optional<MarcaEntity> findByNombre(String nombre);
    Page<MarcaEntity> findAll(Pageable pageable);


}
