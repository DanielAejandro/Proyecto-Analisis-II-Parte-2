package com.dbproject.analisis.dos.model.entity;


import lombok.Data;

@Data
public class LoginRequest {
    private String idUsuario;
    private String password;
}

