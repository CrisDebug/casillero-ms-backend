# 🗂️ Microservicio de Casilleros - Sistema Legajos

Este microservicio forma parte del sistema de gestión de legajos y se encarga de la administración de casilleros, los cuales representan unidades organizativas dentro del sistema.

---

## 🧱 Responsabilidad del servicio

Este servicio gestiona:

- Listado de casilleros
- Creación de casilleros
- Consulta de casilleros por ID
- Asociación de casilleros a procesos del sistema

---

## ⚙️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (validación de token)
- Oracle Database XE
- Maven

---

## 🔐 Seguridad

El microservicio está protegido con autenticación JWT:

- Requiere token válido en todas las peticiones
- Integración con microservicio de usuarios
- Seguridad stateless

---

## 📦 Endpoints principales

### 📌 Obtener todos los casilleros

GET /api/casilleros


---

### 📌 Obtener casillero por ID

GET /api/casilleros/{id}

---

### 📌 Crear casillero

POST /api/casilleros

🔗 Integración

Este microservicio se integra con:

👤 Microservicio de usuarios (JWT authentication)
📁 Microservicio de legajos
🖥️ Frontend Angular (consumo API REST)