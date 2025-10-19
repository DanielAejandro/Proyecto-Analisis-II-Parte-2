package com.dbproject.analisis.dos.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbproject.analisis.dos.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByIdUsuarioAndPassword(String idUsuario, String password);
}
