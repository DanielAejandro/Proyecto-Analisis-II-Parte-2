package com.dbproject.analisis.dos.model.dto;


public class TelefonoDTO {
    private String numeroTelefono;
    private String tipoTelefono;

    // Constructores
    public TelefonoDTO() {}

    public TelefonoDTO(String numeroTelefono, String tipoTelefono) {
        this.numeroTelefono = numeroTelefono;
        this.tipoTelefono = tipoTelefono;
    }

    // Getters y Setters
    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }

    public String getTipoTelefono() { return tipoTelefono; }
    public void setTipoTelefono(String tipoTelefono) { this.tipoTelefono = tipoTelefono; }
}