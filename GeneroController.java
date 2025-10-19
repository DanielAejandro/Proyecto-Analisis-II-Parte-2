package com.dbproject.analisis.dos.controller;

import com.dbproject.analisis.dos.model.entity.Genero;
import com.dbproject.analisis.dos.service.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public List<Genero> getAll() {
        return generoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> getById(@PathVariable Long id) {
        return generoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Genero create(@RequestBody Genero genero) {
        genero.setUsuarioCreacion("admin");
        return generoService.save(genero);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> update(@PathVariable Long id, @RequestBody Genero genero) {
        return generoService.findById(id)
                .map(existing -> {
                    existing.setNombre(genero.getNombre());
                    existing.setUsuarioModificacion("admin");
                    return ResponseEntity.ok(generoService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!generoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        generoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}