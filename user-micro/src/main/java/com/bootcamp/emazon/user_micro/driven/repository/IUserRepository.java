package com.bootcamp.emazon.user_micro.driven.repository;


import com.bootcamp.emazon.user_micro.driven.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
    Page<UserEntity> findAll(Pageable pageable);
}
