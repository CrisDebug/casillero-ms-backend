# 🚀 Proyecto Microservicio Casilleros

Este microservicio forma parte de una arquitectura de backend basada en Spring Boot.

## 📌 Descripción

Gestiona la información de casilleros dentro del sistema de legajos, permitiendo:

- Crear casilleros
- Consultar casilleros
- Asociar legajos
- Integración con autenticación JWT

---

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- Oracle Database
- Maven

---

## ⚙️ Configuración del proyecto

### Variables de entorno (application.properties)

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=system
spring.datasource.password=oracle
server.port=8082
