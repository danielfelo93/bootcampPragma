package com.bootcamp.emazon.transaccion_micro.config;

import com.bootcamp.emazon.transaccion_micro.domain.api.ISuministroServicePort;
import com.bootcamp.emazon.transaccion_micro.domain.api.usecase.SuministroService;
import com.bootcamp.emazon.transaccion_micro.domain.spi.ISuministroPersistencePort;
import com.bootcamp.emazon.transaccion_micro.driven.adapter.SuministroAdaptador;
import com.bootcamp.emazon.transaccion_micro.driven.mapper.ISuministroEntityMapper;
import com.bootcamp.emazon.transaccion_micro.driven.repository.ISuministroRepository;
import com.bootcamp.emazon.transaccion_micro.driving.controller.StockClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ConfiguracionBeans {

    private final ISuministroEntityMapper suministroEntityMapper;
    private final ISuministroRepository suministroRepository;
    private final StockClient stockClient;

    @Bean
    public ISuministroPersistencePort suministroPersistencePort() {
        return new SuministroAdaptador(suministroRepository, suministroEntityMapper);
    }

    @Bean
    public ISuministroServicePort suministroServicePort() {
        return new SuministroService(suministroPersistencePort(), stockClient);
    }
}