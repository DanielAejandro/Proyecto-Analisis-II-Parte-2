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

import com.dbproject.analisis.dos.model.entity.StatusCuenta;
import com.dbproject.analisis.dos.service.StatusCuentaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/status-cuenta")
public class StatusCuentaController {

    private final StatusCuentaService service;

    public StatusCuentaController(StatusCuentaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StatusCuenta>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusCuenta> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("StatusCuenta no encontrado con id: " + id));
    }

    @PostMapping
    public ResponseEntity<StatusCuenta> create(@RequestBody StatusCuenta statusCuenta) {
        return ResponseEntity.ok(service.save(statusCuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusCuenta> update(@PathVariable Integer id, @RequestBody StatusCuenta statusCuenta) {
        return ResponseEntity.ok(service.update(id, statusCuenta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


