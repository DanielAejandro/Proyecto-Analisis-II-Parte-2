package com.dbproject.analisis.dos.model.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "saldo_cuenta")
public class SaldoCuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSaldoCuenta;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoActual;
    
    @ManyToOne
    @JoinColumn(name = "id_status_cuenta", nullable = false)
    private StatusCuenta statusCuenta;
    
    // Constructores
    public SaldoCuenta() {
        this.saldoActual = BigDecimal.ZERO;
    }
    
    // Getters y Setters
    public Long getIdSaldoCuenta() { return idSaldoCuenta; }
    public void setIdSaldoCuenta(Long idSaldoCuenta) { this.idSaldoCuenta = idSaldoCuenta; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    
    public BigDecimal getSaldoActual() { return saldoActual; }
    public void setSaldoActual(BigDecimal saldoActual) { this.saldoActual = saldoActual; }
    
    public StatusCuenta getStatusCuenta() { return statusCuenta; }
    public void setStatusCuenta(StatusCuenta statusCuenta) { this.statusCuenta = statusCuenta; }
}
