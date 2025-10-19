package com.dbproject.analisis.dos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dbproject.analisis.dos.model.entity.StatusCuenta;
import com.dbproject.analisis.dos.model.repository.StatusCuentaRepository;


@Service
public class StatusCuentaService {

    private final StatusCuentaRepository repository;

    public StatusCuentaService(StatusCuentaRepository repository) {
        this.repository = repository;
    }

    public List<StatusCuenta> findAll() {
        return repository.findAll();
    }

    public Optional<StatusCuenta> findById(Integer id) {
        return repository.findById(id);
    }

    public StatusCuenta save(StatusCuenta statusCuenta) {
        statusCuenta.setFechaCreacion(LocalDateTime.now());
        return repository.save(statusCuenta);
    }

    public StatusCuenta update(Integer id, StatusCuenta statusCuenta) {
        StatusCuenta existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("StatusCuenta no encontrado con id: " + id));

        existente.setNombre(statusCuenta.getNombre());
        existente.setUsuarioModificacion(statusCuenta.getUsuarioModificacion());
        existente.setFechaModificacion(LocalDateTime.now());

        return repository.save(existente);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

