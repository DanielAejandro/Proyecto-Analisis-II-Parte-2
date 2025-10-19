package com.dbproject.analisis.dos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbproject.analisis.dos.model.entity.TipoDocumento;
import com.dbproject.analisis.dos.service.TipoDocumentoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tipo-documento")
public class TipoDocumentoController {

    private final TipoDocumentoService service;

    public TipoDocumentoController(TipoDocumentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoDocumento>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumento> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("TipoDocumento no encontrado con id: " + id));
    }

    @PostMapping
    public ResponseEntity<TipoDocumento> create(@RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.ok(service.save(tipoDocumento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDocumento> update(@PathVariable Integer id, @RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.ok(service.update(id, tipoDocumento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


