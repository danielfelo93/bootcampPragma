package com.bootcamp.emazon.transaccion_micro.driven.repository;

import com.bootcamp.emazon.transaccion_micro.driven.entity.SuministroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISuministroRepository extends JpaRepository<SuministroEntity, Long> {
    Optional<SuministroEntity> findByArticuloId(Long articuloId);
}
