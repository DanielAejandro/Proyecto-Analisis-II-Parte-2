package com.dbproject.analisis.dos.controller;


import com.dbproject.analisis.dos.model.entity.ClasificacionCuenta;
import com.dbproject.analisis.dos.service.ClasificacionCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/clasificaciones-cuenta")
@CrossOrigin(origins = "*")
public class ClasificacionCuentaController {
    
    @Autowired
    private ClasificacionCuentaService clasificacionCuentaService;
    
    @GetMapping
    public ResponseEntity<List<ClasificacionCuenta>> getAllClasificaciones() {
        try {
            List<ClasificacionCuenta> clasificaciones = clasificacionCuentaService.getAllClasificaciones();
            return new ResponseEntity<>(clasificaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ClasificacionCuenta> getClasificacionById(@PathVariable Long id) {
        try {
            Optional<ClasificacionCuenta> clasificacion = clasificacionCuentaService.getClasificacionById(id);
            if (clasificacion.isPresent()) {
                return new ResponseEntity<>(clasificacion.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ClasificacionCuenta> getClasificacionByCodigo(@PathVariable String codigo) {
        try {
            Optional<ClasificacionCuenta> clasificacion = clasificacionCuentaService.getClasificacionByCodigo(codigo);
            if (clasificacion.isPresent()) {
                return new ResponseEntity<>(clasificacion.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/naturaleza/{naturaleza}")
    public ResponseEntity<List<ClasificacionCuenta>> getClasificacionesByNaturaleza(@PathVariable String naturaleza) {
        try {
            List<ClasificacionCuenta> clasificaciones = clasificacionCuentaService.getClasificacionesByNaturaleza(naturaleza.toUpperCase());
            return new ResponseEntity<>(clasificaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> createClasificacion(@RequestBody ClasificacionCuenta clasificacionCuenta) {
        try {
            ClasificacionCuenta nuevaClasificacion = clasificacionCuentaService.createClasificacion(clasificacionCuenta);
            return new ResponseEntity<>(nuevaClasificacion, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error interno del servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClasificacion(@PathVariable Long id, @RequestBody ClasificacionCuenta clasificacionDetails) {
        try {
            ClasificacionCuenta clasificacionActualizada = clasificacionCuentaService.updateClasificacion(id, clasificacionDetails);
            return new ResponseEntity<>(clasificacionActualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error interno del servidor");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteClasificacion(@PathVariable Long id) {
        try {
            boolean deleted = clasificacionCuentaService.deleteClasificacion(id);
            Map<String, String> response = new HashMap<>();
            if (deleted) {
                response.put("message", "Clasificación eliminada exitosamente");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("error", "Clasificación no encontrada");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error interno del servidor");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ClasificacionCuenta>> searchClasificaciones(@RequestParam String term) {
        try {
            List<ClasificacionCuenta> clasificaciones = clasificacionCuentaService.searchClasificaciones(term);
            return new ResponseEntity<>(clasificaciones, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}