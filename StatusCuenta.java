package com.dbproject.analisis.dos.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "status_cuenta")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StatusCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdStatusCuenta")
    private Integer idStatusCuenta;

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
}
