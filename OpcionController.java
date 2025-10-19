package com.dbproject.analisis.dos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbproject.analisis.dos.model.entity.Opcion;
import com.dbproject.analisis.dos.service.OpcionService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/opcion")
public class OpcionController {

    private final OpcionService opcionService;

    public OpcionController(OpcionService opcionService) {
        this.opcionService = opcionService;
    }

    @GetMapping
    public List<Opcion> getAll() {
        return opcionService.findAll();
    }

    @GetMapping("/{id}")
    public Opcion getById(@PathVariable Integer id) {
        return opcionService.findById(id).orElse(null);
    }

    @PostMapping
    public Opcion create(@RequestBody Opcion opcion) {
        return opcionService.save(opcion);
    }

    @PutMapping("/{id}")
    public Opcion update(@PathVariable Integer id, @RequestBody Opcion opcion) {
        opcion.setIdOpcion(id);
        return opcionService.save(opcion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        opcionService.deleteById(id);
    }

}
