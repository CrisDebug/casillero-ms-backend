package com.software.casilleros_service.config;

import com.software.casilleros_service.security.JwtFilter;
import com.software.casilleros_service.security.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    /**
     * 🔐 Creamos el utilitario JWT como Bean de Spring
     * Esto permite que pueda ser inyectado en otros componentes
     */
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

    /**
     * 🔐 Creamos el filtro JWT como Bean
     * IMPORTANTE: recibe JwtUtil porque lo necesita para validar tokens
     */
    @Bean
    public JwtFilter jwtFilter(JwtUtil jwtUtil) {
        return new JwtFilter(jwtUtil);
    }

    /**
     * 🛡️ Configuración principal de seguridad del microservicio
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

    http
        // ❌ CSRF desactivado (JWT)
        .csrf(csrf -> csrf.disable())

        // 🌐 CORS habilitado AQUÍ (IMPORTANTE)
        .cors(cors -> {})

        // 🧠 stateless API
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        // 🔐 reglas de seguridad
        .authorizeHttpRequests(auth -> auth

            .requestMatchers("/api/public/**").permitAll()

            .requestMatchers("/casilleros/**").authenticated()

            .anyRequest().authenticated()
        )

        // 🔧 JWT filter
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
    }

    @Bean
public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {

    org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();

    config.addAllowedOrigin("http://localhost:4200");
    config.addAllowedMethod("*");
    config.addAllowedHeader("*");

    org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
            new org.springframework.web.cors.UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/**", config);

    return source;
    }
}