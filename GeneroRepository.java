package com.dbproject.analisis.dos.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbproject.analisis.dos.model.entity.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    Optional<Genero> findById(Long id);

}
