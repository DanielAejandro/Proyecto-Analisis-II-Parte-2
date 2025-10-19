package com.dbproject.analisis.dos.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "clasificacion_cuenta")
public class ClasificacionCuenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo", unique = true, nullable = false, length = 10)
    private String codigo;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    
    @Column(name = "naturaleza", nullable = false, length = 20)
    private String naturaleza; // DEUDORA o ACREEDORA
    
    @Column(name = "activo", nullable = false)
    private Boolean activo = true;
    
    // Constructores
    public ClasificacionCuenta() {}
    
    public ClasificacionCuenta(String codigo, String nombre, String descripcion, String naturaleza) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.naturaleza = naturaleza;
        this.activo = true;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNaturaleza() {
        return naturaleza;
    }
    
    public void setNaturaleza(String naturaleza) {
        this.naturaleza = naturaleza;
    }
    
    public Boolean getActivo() {
        return activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}