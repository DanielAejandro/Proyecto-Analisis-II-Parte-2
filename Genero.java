package com.dbproject.analisis.dos.model.entity;


import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "genero")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGenero")
    private Integer idGenero;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 100)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;

    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
        if (this.usuarioCreacion == null) {
            this.usuarioCreacion = "system"; // O puedes tomar del usuario autenticado
        }
    }

    @PreUpdate
    protected void onUpdate() {
        fechaModificacion = LocalDateTime.now();
        if (usuarioModificacion == null) {
            usuarioModificacion = "system";
        }
    }
}