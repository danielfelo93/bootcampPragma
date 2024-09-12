/*
package com.bootcamp.emazon.stock_micro.domain;

import com.bootcamp.emazon.stock_micro.domain.api.IRemoteUserDetailsServicePort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RemoteUserDetailsServiceAdapter implements UserDetailsService {
    private final IRemoteUserDetailsServicePort remoteUserDetailsServicePort;

    public RemoteUserDetailsServiceAdapter(IRemoteUserDetailsServicePort remoteUserDetailsServicePort) {
        this.remoteUserDetailsServicePort = remoteUserDetailsServicePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //return remoteUserDetailsServicePort.findUserByUsername(username);
        throw new UsernameNotFoundException("User not found");
    }
}
*/
