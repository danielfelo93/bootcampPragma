package com.bootcamp.emazonapi.config;

import com.bootcamp.emazonapi.domain.api.IMarcaServicePort;
import com.bootcamp.emazonapi.domain.api.usecase.MarcaUseCase;
import com.bootcamp.emazonapi.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazonapi.driven.adapter.MarcaAdaptador;
import com.bootcamp.emazonapi.driven.mapper.IMarcaEntityMapper;
import com.bootcamp.emazonapi.driven.repository.IMarcaRepository;
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
    private final IMarcaEntityMapper marcaEntityMapper;
    private final IMarcaRepository marcaRepository;

    @Bean
    public ICategoriaPersistencePort categoriaPersistencePort(){
        return new CategoriaAdaptador(categoriaRepository, categoriaEntityMapper);
    }

    @Bean
    public ICategoriaServicePort categoriaServicePort() { return new CategoriaUseCase(categoriaPersistencePort());}

    @Bean
    public IMarcaPersistencePort marcaPersistencePort(){
        return new MarcaAdaptador(marcaRepository, marcaEntityMapper);
    }

    @Bean
    public IMarcaServicePort marcaServicePort() { return new MarcaUseCase(marcaPersistencePort());}

}

