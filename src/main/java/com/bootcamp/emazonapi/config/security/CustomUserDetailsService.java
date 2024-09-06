/*
package com.bootcamp.emazonapi.config.security;



import com.bootcamp.emazonapi.domain.spi.IUserPersistencePort;
import com.bootcamp.emazonapi.domain.service.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserPersistencePort userPersistencePort;
    private final BCryptPasswordEncoder passwordEncoder;

    public CustomUserDetailsService(IUserPersistencePort userPersistencePort, BCryptPasswordEncoder passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;

        crearUsuarioPorDefecto();
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        User user = userPersistencePort.encontrarPorCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        // Convertir el rol en un objeto SimpleGrantedAuthority
        String rol = user.getRol().getRoleName(); // Obtener el nombre del rol desde el enum
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rol);

        return new org.springframework.security.core.userdetails.User(
                user.getCorreo(),
                user.getContrasena(),
                Collections.singletonList(authority)
        );
    }

    // Metodo para crear un usuario por defecto
    private void crearUsuarioPorDefecto() {
        if (!userPersistencePort.encontrarPorCorreo("admin@default.com").isPresent()) {
            User defaultUser = new User();
            defaultUser.setCorreo("admin@default.com");
            defaultUser.setContrasena(passwordEncoder.encode("password")); // Usar PasswordEncoder
            defaultUser.setRol(UserRole.ADMIN);
            userPersistencePort.guardarUsuario(defaultUser);
        }
    }
}
*/
