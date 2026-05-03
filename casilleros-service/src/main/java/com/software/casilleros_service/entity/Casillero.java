package com.software.casilleros_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
 * ENTIDAD JPA:
 * Representa la tabla CASILLERO en Oracle.
 * Cada objeto Casillero es una fila en la BD.
 */
@Entity
@Table(name = "CASILLERO", schema = "USUARIO_CAS")
public class Casillero {

    /*
     * PRIMARY KEY
     * IDENTITY => Oracle genera el ID automáticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Campo de negocio (ID manual opcional)
     * Útil para lógica del sistema o integración futura
     */
    @Column(name = "MANUAL_ID", unique = true)
    private Long manualId;

    /*
     * Nombre del casillero
     */
    @Column(name = "NOMBRE_CASILLERO", nullable = false)
    private String nombreCasillero;

    /*
     * Auditoría: fecha creación
     */
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    /*
     * Auditoría: fecha actualización
     */
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    /*
     * Hook automático antes de insertar
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    /*
     * Hook automático antes de actualizar
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // =========================
    // GETTERS Y SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public Long getManualId() {
        return manualId;
    }

    public void setManualId(Long manualId) {
        this.manualId = manualId;
    }

    public String getNombreCasillero() {
        return nombreCasillero;
    }

    public void setNombreCasillero(String nombreCasillero) {
        this.nombreCasillero = nombreCasillero;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}