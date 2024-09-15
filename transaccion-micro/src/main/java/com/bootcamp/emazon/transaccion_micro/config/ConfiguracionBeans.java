package com.bootcamp.emazon.transaccion_micro.config;


import com.bootcamp.emazon.transaccion_micro.domain.api.IArticuloServicePort;
import com.bootcamp.emazon.transaccion_micro.domain.api.usecase.ArticuloUseCase;
import com.bootcamp.emazon.transaccion_micro.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazon.transaccion_micro.driven.adapter.ArticuloAdaptador;
import com.bootcamp.emazon.transaccion_micro.driven.mapper.IArticuloEntityMapper;
import com.bootcamp.emazon.transaccion_micro.driven.repository.IArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConfiguracionBeans {

    private final IArticuloEntityMapper articuloEntityMapper;
    private final IArticuloRepository articuloRepository;


    @Bean
    public IArticuloPersistencePort articuloPersistencePort() {
        return new ArticuloAdaptador(articuloRepository, articuloEntityMapper);
    }

    @Bean
    public IArticuloServicePort articuloServicePort() {
        return new ArticuloUseCase(articuloPersistencePort());
    }
}