package com.bootcamp.emazon.user_micro.config;

import com.bootcamp.emazon.user_micro.config.security.JwtService;
import com.bootcamp.emazon.user_micro.domain.api.IUserServicePort;
import com.bootcamp.emazon.user_micro.domain.api.usecase.UserService;
import com.bootcamp.emazon.user_micro.domain.spi.IUserPersistencePort;
import com.bootcamp.emazon.user_micro.driven.adapter.UserAdaptador;
import com.bootcamp.emazon.user_micro.driven.mapper.UserEntityMapper;
import com.bootcamp.emazon.user_micro.driven.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

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
