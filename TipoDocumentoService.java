package com.dbproject.analisis.dos.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dbproject.analisis.dos.model.entity.TipoDocumento;
import com.dbproject.analisis.dos.model.repository.TipoDocumentoRepository;


@Service
public class TipoDocumentoService {

    private final TipoDocumentoRepository repository;

    public TipoDocumentoService(TipoDocumentoRepository repository) {
        this.repository = repository;
    }

    public List<TipoDocumento> findAll() {
        return repository.findAll();
    }

    public Optional<TipoDocumento> findById(Integer id) {
        return repository.findById(id);
    }

    public TipoDocumento save(TipoDocumento tipoDocumento) {
        tipoDocumento.setFechaCreacion(LocalDateTime.now());
        return repository.save(tipoDocumento);
    }

    public TipoDocumento update(Integer id, TipoDocumento tipoDocumento) {
        TipoDocumento existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("TipoDocumento no encontrado con id: " + id));

        existente.setNombre(tipoDocumento.getNombre());
        existente.setUsuarioModificacion(tipoDocumento.getUsuarioModificacion());
        existente.setFechaModificacion(LocalDateTime.now());

        return repository.save(existente);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}

