package com.dbproject.analisis.dos.model.repository;


import com.dbproject.analisis.dos.model.entity.SaldoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaldoCuentaRepository extends JpaRepository<SaldoCuenta, Long> {
    
    Optional<SaldoCuenta> findByUsuarioIdUsuario(Long idUsuario);
    
    @Query("SELECT s FROM SaldoCuenta s WHERE s.idSaldoCuenta = :idSaldoCuenta AND s.statusCuenta.nombre = 'ACTIVA'")
    Optional<SaldoCuenta> findActiveById(@Param("idSaldoCuenta") Long idSaldoCuenta);
}