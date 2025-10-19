package com.dbproject.analisis.dos.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "tipo_movimiento_cxc")
public class TipoMovimientoCXC {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoMovimientoCXC;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(length = 10)
    private String signo; // "+" para ingresos, "-" para egresos
    
    @Column(length = 500)
    private String descripcion;
    
    // Constructores
    public TipoMovimientoCXC() {}
    
    // Getters y Setters
    public Long getIdTipoMovimientoCXC() { return idTipoMovimientoCXC; }
    public void setIdTipoMovimientoCXC(Long idTipoMovimientoCXC) { this.idTipoMovimientoCXC = idTipoMovimientoCXC; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getSigno() { return signo; }
    public void setSigno(String signo) { this.signo = signo; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}