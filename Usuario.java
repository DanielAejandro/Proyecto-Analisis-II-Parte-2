package com.dbproject.analisis.dos.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Usuario {

    @Id
    @Column(name = "IdUsuario", length = 100, nullable = false)
    private String idUsuario;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "FechaNacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "Password", nullable = false, length = 100)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdGenero", nullable = false)
    private Genero genero;

    @Column(name = "UltimaFechaIngreso")
    private LocalDateTime ultimaFechaIngreso;

    @Column(name = "IntentosDeAcceso")
    private Integer intentosDeAcceso;

    @Column(name = "SesionActual", length = 100)
    private String sesionActual;

    @Column(name = "UltimaFechaCambioPassword")
    private LocalDateTime ultimaFechaCambioPassword;

    @Column(name = "CorreoElectronico", length = 100)
    private String correoElectronico;

    @Column(name = "RequiereCambiarPassword")
    private Integer requiereCambiarPassword;

    @Lob
    @Column(name = "Fotografia")
    private byte[] fotografia;

    @Column(name = "TelefonoMovil", length = 30)
    private String telefonoMovil;

    @Column(name = "Pregunta", nullable = false, length = 200)
    private String pregunta;

    @Column(name = "Respuesta", nullable = false, length = 200)
    private String respuesta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdRole", nullable = false)
    private Role role;

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
    // Establecer la fecha de creación si no está definida
    if (this.fechaCreacion == null) {
        this.fechaCreacion = LocalDateTime.now();
    }
    if (this.usuarioCreacion == null) {
        this.usuarioCreacion = "system"; // Valor por defecto
    }
}

@PreUpdate
protected void onUpdate() {
    // No actualizar la fecha de creación durante una actualización
    this.fechaModificacion = LocalDateTime.now();
    if (this.usuarioModificacion == null) {
        this.usuarioModificacion = "system"; // Valor por defecto
    }

}

public String getUsername() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
}
}
