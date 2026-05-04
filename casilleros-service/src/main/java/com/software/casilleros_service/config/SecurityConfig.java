package com.software.casilleros_service.config;

import com.software.casilleros_service.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔓 deshabilitamos CSRF (JWT no usa sesiones)
            .csrf(csrf -> csrf.disable())

            // 🧠 API stateless
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🛡️ reglas de acceso
            .authorizeHttpRequests(auth -> auth

                // opcional: endpoints públicos
                .requestMatchers("/api/public/**").permitAll()

                // 🔐 todo lo demás protegido
                .requestMatchers("/casilleros/**").authenticated()

                .anyRequest().authenticated()
            )

            // 🔧 filtro JWT antes del filtro de Spring Security
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}