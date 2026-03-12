package com.openweminars.servidor.security;

import com.openweminars.servidor.model.Usuario;
import com.openweminars.servidor.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;



    public CustomUserDetailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.obtenerUsuarioPorUsername(username);

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole())
                .build();
    }
}
