package com.dbproject.analisis.dos.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dbproject.analisis.dos.model.entity.EstadoCivil;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {

    Optional<EstadoCivil> findById(Long id);
}


