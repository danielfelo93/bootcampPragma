package com.bootcamp.emazonapi.driven.repository;

import com.bootcamp.emazonapi.driven.entity.ArticuloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IArticuloCategoriaRepository extends JpaRepository<ArticuloEntity, Long> {

    //List<ArticuloCategoriaEntity> findByCategoriaId(Long categoriaId);
    //<ArticuloCategoriaEntity> findByArticuloId(Long articuloId);
}
