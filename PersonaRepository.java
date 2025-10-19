package com.dbproject.analisis.dos.model.repository;


import com.dbproject.analisis.dos.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    
    Optional<Persona> findByNumeroIdentificacion(String numeroIdentificacion);
    
    List<Persona> findByEsClienteTrue();
    
    List<Persona> findByEsSocioTrue();
    
    List<Persona> findByNombreCompletoContainingIgnoreCase(String nombre);
    
    @Query("SELECT p FROM Persona p WHERE p.esCliente = true OR p.esSocio = true")
    List<Persona> findClientesYSocios();
    
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
    
    @Query("SELECT p FROM Persona p WHERE p.numeroIdentificacion LIKE %:filter% OR p.nombreCompleto LIKE %:filter%")
    List<Persona> findByNumeroIdentificacionOrNombreCompletoContaining(@Param("filter") String filter);
}