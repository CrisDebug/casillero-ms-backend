package com.software.casilleros_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 🔍 1. Leer header Authorization
        String authHeader = request.getHeader("Authorization");

        // ❌ si no viene token, dejar pasar (Spring decidirá 403 si corresponde)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 🔐 2. Extraer token puro
        String token = authHeader.substring(7);

        // ❌ validar firma + expiración
        if (!jwtUtil.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 📦 3. Extraer datos del token
        String email = jwtUtil.extractEmail(token);
        String rol = jwtUtil.extractRol(token);

        // 🧠 4. Convertir rol a formato Spring Security
        // IMPORTANTE: Spring usa prefijo ROLE_
        List<SimpleGrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority("ROLE_" + rol));

        // 🛡️ 5. Crear autenticación (usuario autenticado en contexto)
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        email,        // usuario (principal)
                        null,         // password no se usa aquí
                        authorities   // roles del token
                );

        // 📌 6. Asociar request actual (IP, session info, etc.)
        auth.setDetails(request);

        // 🔐 7. Guardar autenticación en contexto de Spring Security
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 🚀 8. Continuar cadena de filtros
        filterChain.doFilter(request, response);
    }
}