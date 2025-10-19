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

import com.dbproject.analisis.dos.model.entity.EstadoCivil;
import com.dbproject.analisis.dos.service.EstadoCivilService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/estado-civil")
public class EstadoCivilController {

    private final EstadoCivilService service;

    public EstadoCivilController(EstadoCivilService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoCivil>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
public ResponseEntity<EstadoCivil> getById(@PathVariable Long id) {
    return service.findById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new RuntimeException("EstadoCivil no encontrado con id: " + id));
}

    @PostMapping
    public ResponseEntity<EstadoCivil> create(@RequestBody EstadoCivil estadoCivil) {
        return ResponseEntity.ok(service.save(estadoCivil));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoCivil> update(@PathVariable Integer id, @RequestBody EstadoCivil estadoCivil) {
        return ResponseEntity.ok(service.update(id, estadoCivil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


