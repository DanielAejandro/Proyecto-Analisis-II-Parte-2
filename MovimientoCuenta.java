package com.dbproject.analisis.dos.model.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento_cuenta")
public class MovimientoCuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovimiento;
    
    @ManyToOne
    @JoinColumn(name = "id_saldo_cuenta", nullable = false)
    private SaldoCuenta saldoCuenta;
    
    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento_cxc", nullable = false)
    private TipoMovimientoCXC tipoMovimiento;
    
    @Column(nullable = false)
    private LocalDateTime fechaMovimiento;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorMovimiento;
    
    @Column(length = 50)
    private String documentoRespaldo;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_registro", nullable = false)
    private Usuario usuarioRegistro;
    
    private LocalDateTime fechaRegistro;
    
    // Constructores
    public MovimientoCuenta() {
        this.fechaRegistro = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getIdMovimiento() { return idMovimiento; }
    public void setIdMovimiento(Long idMovimiento) { this.idMovimiento = idMovimiento; }
    
    public SaldoCuenta getSaldoCuenta() { return saldoCuenta; }
    public void setSaldoCuenta(SaldoCuenta saldoCuenta) { this.saldoCuenta = saldoCuenta; }
    
    public TipoMovimientoCXC getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(TipoMovimientoCXC tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
    
    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(LocalDateTime fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
    
    public BigDecimal getValorMovimiento() { return valorMovimiento; }
    public void setValorMovimiento(BigDecimal valorMovimiento) { this.valorMovimiento = valorMovimiento; }
    
    public String getDocumentoRespaldo() { return documentoRespaldo; }
    public void setDocumentoRespaldo(String documentoRespaldo) { this.documentoRespaldo = documentoRespaldo; }
    
    public Usuario getUsuarioRegistro() { return usuarioRegistro; }
    public void setUsuarioRegistro(Usuario usuarioRegistro) { this.usuarioRegistro = usuarioRegistro; }
    
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}