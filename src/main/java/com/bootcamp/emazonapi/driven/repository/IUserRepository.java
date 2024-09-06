package com.bootcamp.emazonapi.driven.repository;

import com.bootcamp.emazonapi.driven.entity.MarcaEntity;
import com.bootcamp.emazonapi.driven.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCorreo(String correo);
    Page<UserEntity> findAll(Pageable pageable);
}
