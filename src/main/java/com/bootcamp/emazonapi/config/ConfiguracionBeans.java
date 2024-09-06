package com.bootcamp.emazonapi.config;

//import com.bootcamp.emazonapi.config.security.JwtUtil;
import com.bootcamp.emazonapi.domain.api.IArticuloServicePort;
import com.bootcamp.emazonapi.domain.api.IMarcaServicePort;
import com.bootcamp.emazonapi.domain.api.IUserServicePort;
import com.bootcamp.emazonapi.domain.api.usecase.ArticuloUseCase;
import com.bootcamp.emazonapi.domain.api.usecase.MarcaUseCase;
import com.bootcamp.emazonapi.domain.api.usecase.UserService;
import com.bootcamp.emazonapi.domain.spi.IArticuloPersistencePort;
import com.bootcamp.emazonapi.domain.spi.IMarcaPersistencePort;
import com.bootcamp.emazonapi.domain.spi.IUserPersistencePort;
import com.bootcamp.emazonapi.driven.adapter.ArticuloAdaptador;
import com.bootcamp.emazonapi.driven.adapter.MarcaAdaptador;

import com.bootcamp.emazonapi.driven.adapter.UserAdaptador;
import com.bootcamp.emazonapi.driven.mapper.IArticuloEntityMapper;
import com.bootcamp.emazonapi.driven.mapper.IMarcaEntityMapper;
import com.bootcamp.emazonapi.driven.mapper.UserEntityMapper;
import com.bootcamp.emazonapi.driven.repository.IArticuloRepository;
import com.bootcamp.emazonapi.driven.repository.IMarcaRepository;
import com.bootcamp.emazonapi.driven.repository.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bootcamp.emazonapi.domain.api.ICategoriaServicePort;
import com.bootcamp.emazonapi.domain.api.usecase.CategoriaUseCase;
import com.bootcamp.emazonapi.domain.spi.ICategoriaPersistencePort;
import com.bootcamp.emazonapi.driven.adapter.CategoriaAdaptador;
import com.bootcamp.emazonapi.driven.mapper.ICategoriaEntityMapper;
import com.bootcamp.emazonapi.driven.repository.ICategoriaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ConfiguracionBeans {

    private final ICategoriaEntityMapper categoriaEntityMapper;
    private final ICategoriaRepository categoriaRepository;
    private final IMarcaEntityMapper marcaEntityMapper;
    private final IMarcaRepository marcaRepository;
    private final IArticuloEntityMapper articuloEntityMapper;
    private final IArticuloRepository articuloRepository;
    private final UserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final JwtUtil jwtUtil;




    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdaptador(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserService(userPersistencePort(), passwordEncoder/*, jwtUtil*/);
    }

    @Bean
    public ICategoriaPersistencePort categoriaPersistencePort(){
        return new CategoriaAdaptador(categoriaRepository, categoriaEntityMapper);
    }

    @Bean
    public ICategoriaServicePort categoriaServicePort() {
        return new CategoriaUseCase(categoriaPersistencePort());}

    @Bean
    public IMarcaPersistencePort marcaPersistencePort(){
        return new MarcaAdaptador(marcaRepository, marcaEntityMapper);
    }

    @Bean
    public IMarcaServicePort marcaServicePort() { return new MarcaUseCase(marcaPersistencePort());}

    @Bean
    public IArticuloPersistencePort articuloPersistencePort(){
        return new ArticuloAdaptador(articuloRepository, articuloEntityMapper);
    }

    @Bean
    public IArticuloServicePort articuloServicePort() { return new ArticuloUseCase(articuloPersistencePort(), categoriaPersistencePort(), marcaPersistencePort());}

}

