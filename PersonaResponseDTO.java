package com.dbproject.analisis.dos.model.dto;


import java.time.LocalDateTime;
import java.util.List;

public class PersonaResponseDTO {
    private Long id;
    private String nombreCompleto;
    private String direccion;
    private String correoElectronico;
    private String numeroIdentificacion;
    private String tipoDocumento;
    private String estadoCivil;
    private String genero;
    private Boolean esCliente;
    private Boolean esSocio;
    private List<TelefonoDTO> telefonos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // Constructores
    public PersonaResponseDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }

    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Boolean getEsCliente() { return esCliente; }
    public void setEsCliente(Boolean esCliente) { this.esCliente = esCliente; }

    public Boolean getEsSocio() { return esSocio; }
    public void setEsSocio(Boolean esSocio) { this.esSocio = esSocio; }

    public List<TelefonoDTO> getTelefonos() { return telefonos; }
    public void setTelefonos(List<TelefonoDTO> telefonos) { this.telefonos = telefonos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}