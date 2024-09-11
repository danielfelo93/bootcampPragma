package com.bootcamp.emazonapi.config;

import com.bootcamp.emazonapi.domain.api.*;
import com.bootcamp.emazonapi.domain.api.usecase.*;
import com.bootcamp.emazonapi.domain.spi.*;
import com.bootcamp.emazonapi.driven.adapter.*;
import com.bootcamp.emazonapi.driven.mapper.*;
import com.bootcamp.emazonapi.driven.repository.*;
import com.bootcamp.emazonapi.config.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ConfiguracionBeans {

    private final UserEntityMapper userEntityMapper;
    private final JwtService jwtService;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserAdaptador(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserService(userPersistencePort(), passwordEncoder, jwtService, authenticationManager);
    }


}
