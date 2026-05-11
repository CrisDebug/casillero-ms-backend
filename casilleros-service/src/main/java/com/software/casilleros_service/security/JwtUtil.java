// package com.software.casilleros_service.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;

// @Component
// public class JwtUtil {

//     // 🔐 ahora viene desde application.properties (RECOMENDADO)
//     @Value("${jwt.secret}")
//     private String SECRET_KEY;

//     // ⏱️ expiración (solo referencia, no se usa para validar aquí si ya viene en token)
//     @Value("${jwt.expiration:3600000}")
//     private long EXPIRATION_TIME;

//     // 🔑 generar key de firma
//     private Key getSigningKey() {
//         return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//     }

//     // 📦 extraer todos los claims del token
//     private Claims extractAllClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(getSigningKey())
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     // 📧 extraer email (subject)
//     public String extractEmail(String token) {
//         return extractAllClaims(token).getSubject();
//     }

//     // 🧑‍💼 extraer rol
//     public String extractRol(String token) {
//         return extractAllClaims(token).get("rol", String.class);
//     }

//     // ⏰ validar expiración
//     public boolean isTokenExpired(String token) {
//         Date expiration = extractAllClaims(token).getExpiration();
//         return expiration.before(new Date());
//     }

//     // ✅ validar token completo
//     public boolean isTokenValid(String token) {
//         try {
//             return !isTokenExpired(token);
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }

package com.software.casilleros_service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔐 MISMA CLAVE QUE LEGADOS (IMPORTANTE)
    private final String SECRET_KEY = "mi_clave_super_secreta_muy_larga_para_jwt_123456";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRol(String token) {
        return extractAllClaims(token).get("rol", String.class);
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}