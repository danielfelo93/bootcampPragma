/*
package com.bootcamp.emazon.stock_micro.domain.api.usecase;

import com.bootcamp.emazon.stock_micro.domain.api.IRemoteUserDetailsServicePort;
import com.bootcamp.emazon.stock_micro.domain.service.User;
import com.bootcamp.emazon.stock_micro.driving.dto.response.UserDetailsResponse;
import com.bootcamp.emazon.stock_micro.driving.mapper.IUserDetailsResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RemoteUserDetailsService implements IRemoteUserDetailsServicePort {

    private final IUserDetailsResponseMapper userDetailsResponseMapper;
    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Override
    public UserDetails getUserDetails(String username) throws UsernameNotFoundException {
        try {
            UserDetailsResponse response = restTemplate.getForObject(
                    userServiceUrl + "?username=" + username,
                    UserDetailsResponse.class
            );

            if (response == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }

            // Mapea UserDetailsResponse a User
            User user = userDetailsResponseMapper.mapToUser(response);
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("Error al obtener detalles del usuario", e);
        }
    }

}
*/
