package com.dbproject.analisis.dos.service;

import java.util.Optional;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dbproject.analisis.dos.model.entity.Usuario;
import com.dbproject.analisis.dos.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(String id) {
        return usuarioRepository.findById(id);
    }

  public Usuario save(Usuario usuario) {
    // Si es un nuevo usuario, asigna la fecha de creación
    if (usuario.getFechaCreacion() == null) {
        usuario.setFechaCreacion(LocalDateTime.now());
    }

    // Si el campo de usuarioCreacion es nulo, asigna un valor por defecto
    if (usuario.getUsuarioCreacion() == null || usuario.getUsuarioCreacion().isEmpty()) {
        usuario.setUsuarioCreacion("system"); // Asigna un valor por defecto
    }

    return usuarioRepository.save(usuario);
}

    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }
}