package com.dbproject.analisis.dos.service;

import java.security.MessageDigest;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dbproject.analisis.dos.model.entity.LoginRequest;
import com.dbproject.analisis.dos.model.entity.Usuario;
import com.dbproject.analisis.dos.model.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> login(LoginRequest loginRequest) {
        String encryptedPassword = md5(loginRequest.getPassword());

        return usuarioRepository.findByIdUsuarioAndPassword(loginRequest.getIdUsuario(), encryptedPassword);
    }

    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar con MD5", e);
        }
    }
}
