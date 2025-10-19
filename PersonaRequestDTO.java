package com.dbproject.analisis.dos.model.dto;


import java.util.List;

public class PersonaRequestDTO {
    private String nombreCompleto;
    private String direccion;
    private String correoElectronico;
    private String numeroIdentificacion;
    private Integer tipoDocumentoId;
    private Long estadoCivilId;
    private Long generoId;
    private Boolean esCliente;
    private Boolean esSocio;
    private List<TelefonoDTO> telefonos;

    // Constructores
    public PersonaRequestDTO() {}

    // Getters y Setters
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }

    public Integer getTipoDocumentoId() { return tipoDocumentoId; }
    public void setTipoDocumentoId(Integer tipoDocumentoId) { this.tipoDocumentoId = tipoDocumentoId; }

    public Long getEstadoCivilId() { return estadoCivilId; }
    public void setEstadoCivilId(Long estadoCivilId) { this.estadoCivilId = estadoCivilId; }

    public Long getGeneroId() { return generoId; }
    public void setGeneroId(Long generoId) { this.generoId = generoId; }

    public Boolean getEsCliente() { return esCliente; }
    public void setEsCliente(Boolean esCliente) { this.esCliente = esCliente; }

    public Boolean getEsSocio() { return esSocio; }
    public void setEsSocio(Boolean esSocio) { this.esSocio = esSocio; }

    public List<TelefonoDTO> getTelefonos() { return telefonos; }
    public void setTelefonos(List<TelefonoDTO> telefonos) { this.telefonos = telefonos; }
}