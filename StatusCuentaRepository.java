package com.dbproject.analisis.dos.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbproject.analisis.dos.model.entity.StatusCuenta;

@Repository
public interface StatusCuentaRepository extends JpaRepository<StatusCuenta, Integer> {
}


