    package com.software.casilleros_service.repository;

    import com.software.casilleros_service.entity.Casillero;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    /*
    * REPOSITORY:
    * Capa encargada de comunicarse con la base de datos.
    * Spring Data JPA genera automáticamente las consultas SQL.
    */
    @Repository
    public interface CasilleroRepository extends JpaRepository<Casillero, Long> {

        /*
        * Aquí podríamos agregar consultas personalizadas si las necesitamos.
        * Ejemplo:
        * Optional<Casillero> findByManualId(Long manualId);
        */

    }