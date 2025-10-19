package com.dbproject.analisis.dos.model.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoCuentaResponseDTO {
    
    private Long idMovimiento;
    private Long idSaldoCuenta;
    private String tipoMovimiento;
    private String signoMovimiento;
    private LocalDateTime fechaMovimiento;
    private BigDecimal valorMovimiento;
    private String documentoRespaldo;
    private String usuarioRegistro;
    private LocalDateTime fechaRegistro;
    private BigDecimal saldoAnterior;
    private BigDecimal saldoNuevo;
    
    // Constructores
    public MovimientoCuentaResponseDTO() {}
    
    // Getters y Setters
    public Long getIdMovimiento() { return idMovimiento; }
    public void setIdMovimiento(Long idMovimiento) { this.idMovimiento = idMovimiento; }
    
    public Long getIdSaldoCuenta() { return idSaldoCuenta; }
    public void setIdSaldoCuenta(Long idSaldoCuenta) { this.idSaldoCuenta = idSaldoCuenta; }
    
    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
    
    public String getSignoMovimiento() { return signoMovimiento; }
    public void setSignoMovimiento(String signoMovimiento) { this.signoMovimiento = signoMovimiento; }
    
    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(LocalDateTime fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
    
    public BigDecimal getValorMovimiento() { return valorMovimiento; }
    public void setValorMovimiento(BigDecimal valorMovimiento) { this.valorMovimiento = valorMovimiento; }
    
    public String getDocumentoRespaldo() { return documentoRespaldo; }
    public void setDocumentoRespaldo(String documentoRespaldo) { this.documentoRespaldo = documentoRespaldo; }
    
    public String getUsuarioRegistro() { return usuarioRegistro; }
    public void setUsuarioRegistro(String usuarioRegistro) { this.usuarioRegistro = usuarioRegistro; }
    
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    
    public BigDecimal getSaldoAnterior() { return saldoAnterior; }
    public void setSaldoAnterior(BigDecimal saldoAnterior) { this.saldoAnterior = saldoAnterior; }
    
    public BigDecimal getSaldoNuevo() { return saldoNuevo; }
    public void setSaldoNuevo(BigDecimal saldoNuevo) { this.saldoNuevo = saldoNuevo; }
}