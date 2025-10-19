package com.dbproject.analisis.dos.controller;


import com.dbproject.analisis.dos.model.dto.MovimientoCuentaRequestDTO;
import com.dbproject.analisis.dos.model.dto.MovimientoCuentaResponseDTO;
import com.dbproject.analisis.dos.service.MovimientoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos-cuenta")
public class MovimientoCuentaController {
    
    @Autowired
    private MovimientoCuentaService movimientoCuentaService;
    
    @PostMapping
    public ResponseEntity<MovimientoCuentaResponseDTO> registrarMovimiento(@RequestBody MovimientoCuentaRequestDTO requestDTO) {
        try {
            MovimientoCuentaResponseDTO response = movimientoCuentaService.registrarMovimiento(requestDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    @GetMapping("/cuenta/{idSaldoCuenta}")
    public ResponseEntity<List<MovimientoCuentaResponseDTO>> obtenerMovimientosPorCuenta(@PathVariable Long idSaldoCuenta) {
        List<MovimientoCuentaResponseDTO> movimientos = movimientoCuentaService.obtenerMovimientosPorCuenta(idSaldoCuenta);
        return ResponseEntity.ok(movimientos);
    }
    
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<MovimientoCuentaResponseDTO>> obtenerMovimientosPorUsuario(@PathVariable Long idUsuario) {
        List<MovimientoCuentaResponseDTO> movimientos = movimientoCuentaService.obtenerMovimientosPorUsuario(idUsuario);
        return ResponseEntity.ok(movimientos);
    }
    
    @GetMapping("/{idMovimiento}")
    public ResponseEntity<MovimientoCuentaResponseDTO> obtenerMovimientoPorId(@PathVariable Long idMovimiento) {
        try {
            MovimientoCuentaResponseDTO movimiento = movimientoCuentaService.obtenerMovimientoPorId(idMovimiento);
            return ResponseEntity.ok(movimiento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
