package com.dbproject.analisis.dos.model.repository;


import com.dbproject.analisis.dos.model.entity.ClasificacionCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasificacionCuentaRepository extends JpaRepository<ClasificacionCuenta, Long> {
    
    Optional<ClasificacionCuenta> findByCodigo(String codigo);
    
    List<ClasificacionCuenta> findByActivoTrue();
    
    List<ClasificacionCuenta> findByNaturaleza(String naturaleza);
    
    List<ClasificacionCuenta> findByActivoTrueAndNaturaleza(String naturaleza);
    
    @Query("SELECT c FROM ClasificacionCuenta c WHERE c.activo = true AND (c.codigo LIKE %:searchTerm% OR c.nombre LIKE %:searchTerm%)")
    List<ClasificacionCuenta> searchActiveClasificaciones(@Param("searchTerm") String searchTerm);
    
    boolean existsByCodigo(String codigo);
    
    boolean existsByCodigoAndIdNot(String codigo, Long id);
}