package com.dbproject.analisis.dos.service;


import com.dbproject.analisis.dos.model.entity.*;
import com.dbproject.analisis.dos.model.dto.MovimientoCuentaRequestDTO;
import com.dbproject.analisis.dos.model.dto.MovimientoCuentaResponseDTO;
import com.dbproject.analisis.dos.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoCuentaService {
    
    @Autowired
    private MovimientoCuentaRepository movimientoCuentaRepository;
    
    @Autowired
    private SaldoCuentaRepository saldoCuentaRepository;
    
    @Autowired
    private TipoMovimientoCXCRepository tipoMovimientoCXCRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public MovimientoCuentaResponseDTO registrarMovimiento(MovimientoCuentaRequestDTO requestDTO) {
        // Validar que la cuenta existe y está activa
        SaldoCuenta saldoCuenta = saldoCuentaRepository.findActiveById(requestDTO.getIdSaldoCuenta())
                .orElseThrow(() -> new RuntimeException("La cuenta no existe o no está activa"));
        
        // Validar tipo de movimiento
        TipoMovimientoCXC tipoMovimiento = tipoMovimientoCXCRepository.findById(requestDTO.getIdTipoMovimientoCXC())
                .orElseThrow(() -> new RuntimeException("Tipo de movimiento no válido"));
        
        // CORRECCIÓN: Validar usuario que registra
        Usuario usuarioRegistro = usuarioRepository.findById(requestDTO.getIdUsuarioRegistro())
                .orElseThrow(() -> new RuntimeException("Usuario no válido"));
        
        // Validar monto positivo
        if (requestDTO.getValorMovimiento().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("El monto debe ser mayor a cero");
        }
        
        // Calcular nuevo saldo
        BigDecimal saldoAnterior = saldoCuenta.getSaldoActual();
        BigDecimal valorMovimiento = requestDTO.getValorMovimiento();
        BigDecimal saldoNuevo;
        
        if ("+".equals(tipoMovimiento.getSigno())) {
            saldoNuevo = saldoAnterior.add(valorMovimiento);
        } else if ("-".equals(tipoMovimiento.getSigno())) {
            // Validar que no quede saldo negativo
            if (saldoAnterior.compareTo(valorMovimiento) < 0) {
                throw new RuntimeException("Saldo insuficiente para realizar el movimiento");
            }
            saldoNuevo = saldoAnterior.subtract(valorMovimiento);
        } else {
            throw new RuntimeException("Signo de movimiento no válido");
        }
        
        // Crear y guardar movimiento
        MovimientoCuenta movimiento = new MovimientoCuenta();
        movimiento.setSaldoCuenta(saldoCuenta);
        movimiento.setTipoMovimiento(tipoMovimiento);
        movimiento.setFechaMovimiento(requestDTO.getFechaMovimiento());
        movimiento.setValorMovimiento(valorMovimiento);
        movimiento.setDocumentoRespaldo(requestDTO.getDocumentoRespaldo());
        movimiento.setUsuarioRegistro(usuarioRegistro);
        
        MovimientoCuenta movimientoGuardado = movimientoCuentaRepository.save(movimiento);
        
        // Actualizar saldo de la cuenta
        saldoCuenta.setSaldoActual(saldoNuevo);
        saldoCuentaRepository.save(saldoCuenta);
        
        return convertirAResponseDTO(movimientoGuardado, saldoAnterior, saldoNuevo);
    }
    
    public List<MovimientoCuentaResponseDTO> obtenerMovimientosPorCuenta(Long idSaldoCuenta) {
        return movimientoCuentaRepository.findBySaldoCuentaIdSaldoCuentaOrderByFechaMovimientoDesc(idSaldoCuenta)
                .stream()
                .map(movimiento -> convertirAResponseDTO(movimiento, null, null))
                .collect(Collectors.toList());
    }
    
    public List<MovimientoCuentaResponseDTO> obtenerMovimientosPorUsuario(Long idUsuario) {
        return movimientoCuentaRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(movimiento -> convertirAResponseDTO(movimiento, null, null))
                .collect(Collectors.toList());
    }
    
    public MovimientoCuentaResponseDTO obtenerMovimientoPorId(Long idMovimiento) {
        MovimientoCuenta movimiento = movimientoCuentaRepository.findById(idMovimiento)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        return convertirAResponseDTO(movimiento, null, null);
    }
    
    private MovimientoCuentaResponseDTO convertirAResponseDTO(MovimientoCuenta movimiento, BigDecimal saldoAnterior, BigDecimal saldoNuevo) {
        MovimientoCuentaResponseDTO responseDTO = new MovimientoCuentaResponseDTO();
        responseDTO.setIdMovimiento(movimiento.getIdMovimiento());
        responseDTO.setIdSaldoCuenta(movimiento.getSaldoCuenta().getIdSaldoCuenta());
        responseDTO.setTipoMovimiento(movimiento.getTipoMovimiento().getNombre());
        responseDTO.setSignoMovimiento(movimiento.getTipoMovimiento().getSigno());
        responseDTO.setFechaMovimiento(movimiento.getFechaMovimiento());
        responseDTO.setValorMovimiento(movimiento.getValorMovimiento());
        responseDTO.setDocumentoRespaldo(movimiento.getDocumentoRespaldo());
        responseDTO.setUsuarioRegistro(movimiento.getUsuarioRegistro().getUsername());
        responseDTO.setFechaRegistro(movimiento.getFechaRegistro());
        responseDTO.setSaldoAnterior(saldoAnterior);
        responseDTO.setSaldoNuevo(saldoNuevo);
        
        return responseDTO;
    }
}