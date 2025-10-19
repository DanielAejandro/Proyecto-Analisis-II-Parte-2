package com.dbproject.analisis.dos.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "BITACORA_ACCESO")
@Data
public class BitacoraAcceso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBitacoraAcceso")
    private Integer idBitacoraAcceso;

    @Column(name = "IdUsuario", nullable = false, length = 100)
    private String idUsuario;

    @Column(name = "FechaAcceso", nullable = false)
    private LocalDateTime fechaAcceso;

    @Column(name = "HttpUserAgent", length = 200)
    private String httpUserAgent;

    @Column(name = "DireccionIp", length = 50)
    private String direccionIp;

    @Column(name = "Accion", length = 100)
    private String accion;

    @Column(name = "SistemaOperativo", length = 50)
    private String sistemaOperativo;

    @Column(name = "Dispositivo", length = 50)
    private String dispositivo;

    @Column(name = "Browser", length = 50)
    private String browser;

    @Column(name = "Sesion", length = 100)
    private String sesion;
}
