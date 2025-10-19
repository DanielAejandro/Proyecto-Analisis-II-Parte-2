package com.dbproject.analisis.dos.model.repository;


import com.dbproject.analisis.dos.model.entity.MovimientoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoCuentaRepository extends JpaRepository<MovimientoCuenta, Long> {
    
    List<MovimientoCuenta> findBySaldoCuentaIdSaldoCuentaOrderByFechaMovimientoDesc(Long idSaldoCuenta);
    
    @Query("SELECT m FROM MovimientoCuenta m WHERE m.saldoCuenta.usuario.idUsuario = :idUsuario ORDER BY m.fechaMovimiento DESC")
    List<MovimientoCuenta> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}