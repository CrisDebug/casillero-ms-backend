package com.software.casilleros_service.service;

import com.software.casilleros_service.entity.Casillero;
import com.software.casilleros_service.repository.CasilleroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * SERVICE:
 * Aquí vive la lógica del negocio del microservicio.
 * No debería tener SQL ni lógica de controller.
 */
@Service
public class CasilleroService {

    private final CasilleroRepository casilleroRepository;

    // Inyección por constructor (buena práctica)
    public CasilleroService(CasilleroRepository casilleroRepository) {
        this.casilleroRepository = casilleroRepository;
    }

    /*
     * Obtener todos los casilleros
     */
    public List<Casillero> findAll() {
        return casilleroRepository.findAll();
    }

    /*
     * Obtener casillero por ID
     */
    public Optional<Casillero> findById(Long id) {
        return casilleroRepository.findById(id);
    }

    /*
     * Crear o actualizar casillero
     */
    public Casillero save(Casillero casillero) {
        return casilleroRepository.save(casillero);
    }

    /*
     * Eliminar casillero por ID
     */
    public void deleteById(Long id) {
        casilleroRepository.deleteById(id);
    }
}