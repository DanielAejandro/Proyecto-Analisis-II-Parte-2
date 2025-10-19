package com.dbproject.analisis.dos.model.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoCuentaRequestDTO {
    
    private Long idSaldoCuenta;
    private Long idTipoMovimientoCXC;
    private LocalDateTime fechaMovimiento;
    private BigDecimal valorMovimiento;
    private String documentoRespaldo;
    private String idUsuarioRegistro;
    
    // Constructores
    public MovimientoCuentaRequestDTO() {}
    
    // Getters y Setters
    public Long getIdSaldoCuenta() { return idSaldoCuenta; }
    public void setIdSaldoCuenta(Long idSaldoCuenta) { this.idSaldoCuenta = idSaldoCuenta; }
    
    public Long getIdTipoMovimientoCXC() { return idTipoMovimientoCXC; }
    public void setIdTipoMovimientoCXC(Long idTipoMovimientoCXC) { this.idTipoMovimientoCXC = idTipoMovimientoCXC; }
    
    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(LocalDateTime fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
    
    public BigDecimal getValorMovimiento() { return valorMovimiento; }
    public void setValorMovimiento(BigDecimal valorMovimiento) { this.valorMovimiento = valorMovimiento; }
    
    public String getDocumentoRespaldo() { return documentoRespaldo; }
    public void setDocumentoRespaldo(String documentoRespaldo) { this.documentoRespaldo = documentoRespaldo; }
    
    public String getIdUsuarioRegistro() { return idUsuarioRegistro; }
    public void setIdUsuarioRegistro(String idUsuarioRegistro) { this.idUsuarioRegistro = idUsuarioRegistro; }
}
