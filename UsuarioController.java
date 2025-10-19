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

import com.dbproject.analisis.dos.model.entity.Usuario;
import com.dbproject.analisis.dos.service.UsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable String id) {
        return usuarioService.findById(id).orElse(null);
    }

     @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }


    @PutMapping("/{id}")
public Usuario update(@PathVariable String id, @RequestBody Usuario usuario) {
    // Establecer el ID del usuario
    usuario.setIdUsuario(id);

    // No actualizar la fecha de creación durante la actualización
    // Si es necesario, podrías eliminar temporalmente la fechaCreacion antes de guardar
    usuario.setFechaCreacion(null); // Esto asegura que no se sobrescriba accidentalmente

    return usuarioService.save(usuario);
}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        usuarioService.deleteById(id);
    }
}