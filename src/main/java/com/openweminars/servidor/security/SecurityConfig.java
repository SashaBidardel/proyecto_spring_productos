package com.openweminars.servidor.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // SOLO ADMIN
                        .requestMatchers("/usuarios/**").hasRole("ADMIN")

                        .requestMatchers(
                                "/productos/nuevo",
                                "/productos/editar/**",
                                "/productos/borrar/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/categorias/nuevo",
                                "/categorias/editar/**",
                                "/categorias/borrar/**"
                        ).hasRole("ADMIN")

                        // TODOS LOS USUARIOS LOGUEADOS
                        .requestMatchers(
                                "/productos/**",
                                "/categorias/**"
                        ).authenticated()

                        // acceso libre
                        .requestMatchers(
                                "/login",
                                "/registro",
                                "/css/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                .loginPage("/login")   // aquí ponemos nuestra página personalizada
                .permitAll()   // habilita formulario web
                )
                .httpBasic(Customizer.withDefaults()
                );
        return http.build();
    }


}