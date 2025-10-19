package com.dbproject.analisis.dos.model.repository;



import com.dbproject.analisis.dos.model.entity.TipoMovimientoCXC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimientoCXCRepository extends JpaRepository<TipoMovimientoCXC, Long> {
}
