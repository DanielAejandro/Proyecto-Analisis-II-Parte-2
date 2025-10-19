package com.dbproject.analisis.dos.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "opcion")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOpcion")
    private Integer idOpcion;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "OrdenMenu", nullable = false)
    private Integer ordenMenu;

    @Column(name = "Pagina", nullable = false, length = 100)
    private String pagina;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 100)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;
}
