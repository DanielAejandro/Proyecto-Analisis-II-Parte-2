package com.dbproject.analisis.dos.service;


import com.dbproject.analisis.dos.model.entity.ClasificacionCuenta;
import com.dbproject.analisis.dos.model.repository.ClasificacionCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasificacionCuentaService {
    
    @Autowired
    private ClasificacionCuentaRepository clasificacionCuentaRepository;
    
    public List<ClasificacionCuenta> getAllClasificaciones() {
        return clasificacionCuentaRepository.findByActivoTrue();
    }
    
    public Optional<ClasificacionCuenta> getClasificacionById(Long id) {
        return clasificacionCuentaRepository.findById(id);
    }
    
    public Optional<ClasificacionCuenta> getClasificacionByCodigo(String codigo) {
        return clasificacionCuentaRepository.findByCodigo(codigo);
    }
    
    public List<ClasificacionCuenta> getClasificacionesByNaturaleza(String naturaleza) {
        return clasificacionCuentaRepository.findByActivoTrueAndNaturaleza(naturaleza);
    }
    
    public ClasificacionCuenta createClasificacion(ClasificacionCuenta clasificacionCuenta) {
        // Validar que no exista otro con el mismo código
        if (clasificacionCuentaRepository.existsByCodigo(clasificacionCuenta.getCodigo())) {
            throw new RuntimeException("Ya existe una clasificación con el código: " + clasificacionCuenta.getCodigo());
        }
        
        // Validar naturaleza
        if (!isNaturalezaValida(clasificacionCuenta.getNaturaleza())) {
            throw new RuntimeException("Naturaleza inválida. Debe ser 'DEUDORA' o 'ACREEDORA'");
        }
        
        clasificacionCuenta.setActivo(true);
        return clasificacionCuentaRepository.save(clasificacionCuenta);
    }
    
    public ClasificacionCuenta updateClasificacion(Long id, ClasificacionCuenta clasificacionDetails) {
        Optional<ClasificacionCuenta> optionalClasificacion = clasificacionCuentaRepository.findById(id);
        
        if (optionalClasificacion.isPresent()) {
            ClasificacionCuenta clasificacion = optionalClasificacion.get();
            
            // Validar que el nuevo código no exista en otro registro
            if (!clasificacion.getCodigo().equals(clasificacionDetails.getCodigo()) && 
                clasificacionCuentaRepository.existsByCodigoAndIdNot(clasificacionDetails.getCodigo(), id)) {
                throw new RuntimeException("Ya existe otra clasificación con el código: " + clasificacionDetails.getCodigo());
            }
            
            // Validar naturaleza
            if (!isNaturalezaValida(clasificacionDetails.getNaturaleza())) {
                throw new RuntimeException("Naturaleza inválida. Debe ser 'DEUDORA' o 'ACREEDORA'");
            }
            
            clasificacion.setCodigo(clasificacionDetails.getCodigo());
            clasificacion.setNombre(clasificacionDetails.getNombre());
            clasificacion.setDescripcion(clasificacionDetails.getDescripcion());
            clasificacion.setNaturaleza(clasificacionDetails.getNaturaleza());
            
            return clasificacionCuentaRepository.save(clasificacion);
        } else {
            throw new RuntimeException("Clasificación no encontrada con ID: " + id);
        }
    }
    
    public boolean deleteClasificacion(Long id) {
        Optional<ClasificacionCuenta> optionalClasificacion = clasificacionCuentaRepository.findById(id);
        
        if (optionalClasificacion.isPresent()) {
            ClasificacionCuenta clasificacion = optionalClasificacion.get();
            clasificacion.setActivo(false);
            clasificacionCuentaRepository.save(clasificacion);
            return true;
        }
        return false;
    }
    
    public List<ClasificacionCuenta> searchClasificaciones(String searchTerm) {
        return clasificacionCuentaRepository.searchActiveClasificaciones(searchTerm);
    }
    
    private boolean isNaturalezaValida(String naturaleza) {
        return "DEUDORA".equalsIgnoreCase(naturaleza) || "ACREEDORA".equalsIgnoreCase(naturaleza);
    }
}