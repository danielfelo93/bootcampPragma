package com.bootcamp.emazonAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bootcamp.emazonAPI.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonAPI.domain.api.usecase.CategoriaUseCase;
import com.bootcamp.emazonAPI.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonAPI.driven.adapter.CategoriaAdaptador;
import com.bootcamp.emazonAPI.driven.mapper.ICategoriaEntityMapper;
import com.bootcamp.emazonAPI.driven.repository.ICategoriaRepository;

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

