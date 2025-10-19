package com.dbproject.analisis.dos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dbproject.analisis.dos.model.entity.EstadoCivil;
import com.dbproject.analisis.dos.model.repository.EstadoCivilRepository;


@Service
public class EstadoCivilService {

    private final EstadoCivilRepository repository;

    public EstadoCivilService(EstadoCivilRepository repository) {
        this.repository = repository;
    }

    public List<EstadoCivil> findAll() {
        return repository.findAll();
    }

    public Optional<EstadoCivil> findById(Long id) {
        return repository.findById(id);
    }

    public EstadoCivil save(EstadoCivil estadoCivil) {
        estadoCivil.setFechaCreacion(LocalDateTime.now());
        return repository.save(estadoCivil);
    }

    public EstadoCivil update(Integer id, EstadoCivil estadoCivil) {
        EstadoCivil existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("EstadoCivil no encontrado con id: " + id));

        existente.setNombre(estadoCivil.getNombre());
        existente.setUsuarioModificacion(estadoCivil.getUsuarioModificacion());
        existente.setFechaModificacion(LocalDateTime.now());

        return repository.save(existente);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

