package com.software.casilleros_service.controller;

import com.software.casilleros_service.entity.Casillero;
import com.software.casilleros_service.service.CasilleroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * CONTROLLER:
 * Expone las APIs REST del microservicio.
 * Aquí llega la comunicación desde Angular o Postman.
 */
@RestController
@RequestMapping("/api/casilleros")
@CrossOrigin(origins = "*") // temporal para evitar problemas con Angular
public class CasilleroController {

    private final CasilleroService casilleroService;

    public CasilleroController(CasilleroService casilleroService) {
        this.casilleroService = casilleroService;
    }

    /*
     * GET: obtener todos los casilleros
     */
    @GetMapping
    public List<Casillero> getAll() {
        return casilleroService.findAll();
    }

    /*
     * GET: obtener casillero por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Casillero> getById(@PathVariable Long id) {
        return casilleroService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * POST: crear casillero
     */
    @PostMapping
    public Casillero create(@RequestBody Casillero casillero) {
        return casilleroService.save(casillero);
    }

    /*
     * PUT: actualizar casillero
     */
    @PutMapping("/{id}")
    public ResponseEntity<Casillero> update(@PathVariable Long id,
                                            @RequestBody Casillero casillero) {

        return casilleroService.findById(id)
                .map(existing -> {
                    existing.setManualId(casillero.getManualId());
                    existing.setNombreCasillero(casillero.getNombreCasillero());
                    return ResponseEntity.ok(casilleroService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /*
     * DELETE: eliminar casillero
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (casilleroService.findById(id).isPresent()) {
            casilleroService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}