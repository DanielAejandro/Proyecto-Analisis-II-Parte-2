package com.dbproject.analisis.dos.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefonos_persona")
public class TelefonoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_telefono", nullable = false, length = 20)
    private String numeroTelefono;

    @Column(name = "tipo_telefono", length = 50)
    private String tipoTelefono; // Móvil, Casa, Trabajo, etc.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    // Constructores
    public TelefonoPersona() {}

    public TelefonoPersona(String numeroTelefono, String tipoTelefono, Persona persona) {
        this.numeroTelefono = numeroTelefono;
        this.tipoTelefono = tipoTelefono;
        this.persona = persona;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }

    public String getTipoTelefono() { return tipoTelefono; }
    public void setTipoTelefono(String tipoTelefono) { this.tipoTelefono = tipoTelefono; }

    public Persona getPersona() { return persona; }
    public void setPersona(Persona persona) { this.persona = persona; }
}
