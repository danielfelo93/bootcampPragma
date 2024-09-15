package com.bootcamp.emazon.stock_micro.config;

import com.bootcamp.emazon.stock_micro.domain.api.IArticuloServicePort;
import com.bootcamp.emazon.stock_micro.domain.api.IMarcaServicePort;
import com.bootcamp.emazon.stock_micro.domain.api.ICategoriaServicePort;
import com.bootcamp.emazon.stock_micro.domain.api.usecase.ArticuloUseCase;
import com.bootcamp.emazon.stock_micro.domain.api.usecase.MarcaUseCase;
import com.bootcamp.emazon.stock_micro.domain.api.usecase.CategoriaUseCase;
import com.bootcamp.emazon.stock_micro.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazon.stock_micro.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazon.stock_micro.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazon.stock_micro.driven.adapter.ArticuloAdaptador;
import com.bootcamp.emazon.stock_micro.driven.adapter.MarcaAdaptador;
import com.bootcamp.emazon.stock_micro.driven.adapter.CategoriaAdaptador;
import com.bootcamp.emazon.stock_micro.driven.mapper.IArticuloEntityMapper;
import com.bootcamp.emazon.stock_micro.driven.mapper.ICategoriaEntityMapper;
import com.bootcamp.emazon.stock_micro.driven.mapper.IMarcaEntityMapper;
import com.bootcamp.emazon.stock_micro.driven.repository.IArticuloRepository;
import com.bootcamp.emazon.stock_micro.driven.repository.ICategoriaRepository;
import com.bootcamp.emazon.stock_micro.driven.repository.IMarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConfiguracionBeans {

    private final ICategoriaEntityMapper categoriaEntityMapper;
    private final ICategoriaRepository categoriaRepository;
    private final IMarcaEntityMapper marcaEntityMapper;
    private final IMarcaRepository marcaRepository;
    private final IArticuloEntityMapper articuloEntityMapper;
    private final IArticuloRepository articuloRepository;

    @Bean
    public ICategoriaPersistencePort categoriaPersistencePort() {
        return new CategoriaAdaptador(categoriaRepository, categoriaEntityMapper);
    }

    @Bean
    public ICategoriaServicePort categoriaServicePort(ICategoriaPersistencePort categoriaPersistencePort) {
        return new CategoriaUseCase(categoriaPersistencePort);
    }

    @Bean
    public IMarcaPersistencePort marcaPersistencePort() {
        return new MarcaAdaptador(marcaRepository, marcaEntityMapper);
    }

    @Bean
    public IMarcaServicePort marcaServicePort() {
        return new MarcaUseCase(marcaPersistencePort());
    }

    @Bean
    public IArticuloPersistencePort articuloPersistencePort() {
        return new ArticuloAdaptador(articuloRepository, articuloEntityMapper);
    }

    @Bean
    public IArticuloServicePort articuloServicePort() {
        return new ArticuloUseCase(articuloPersistencePort(), categoriaPersistencePort(), marcaPersistencePort());
    }
}