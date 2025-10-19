package com.dbproject.analisis.dos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dbproject.analisis.dos.model.entity.Opcion;
import com.dbproject.analisis.dos.model.repository.OpcionRepository;

@Service
public class OpcionService {

    private final OpcionRepository opcionRepository;

    public OpcionService(OpcionRepository opcionRepository) {
        this.opcionRepository = opcionRepository;
    }

    public List<Opcion> findAll() {
        return opcionRepository.findAll();
    }

    public Optional<Opcion> findById(Integer id) {
        return opcionRepository.findById(id);
    }

    public Opcion save(Opcion opcion) {
        return opcionRepository.save(opcion);
    }

    public void deleteById(Integer id) {
        opcionRepository.deleteById(id);
    }

}
