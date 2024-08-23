package com.bootcamp.emazonapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bootcamp.emazonapi.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonapi.domain.api.usecase.CategoriaUseCase;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.driven.adapter.CategoriaAdaptador;
import com.bootcamp.emazonapi.driven.mapper.ICategoriaEntityMapper;
import com.bootcamp.emazonapi.driven.repository.ICategoriaRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ConfiguracionBeans {

    private final ICategoriaEntityMapper categoriaEntityMapper;
    private final ICategoriaRepository categoriaRepository;

    @Bean
    public ICategoriaPersistencePort categoriaPersistencePort(){
        return new CategoriaAdaptador(categoriaRepository, categoriaEntityMapper);
    }

    @Bean
    public ICategoriaServicePort categoriaServicePort() { return new CategoriaUseCase(categoriaPersistencePort());}

}

