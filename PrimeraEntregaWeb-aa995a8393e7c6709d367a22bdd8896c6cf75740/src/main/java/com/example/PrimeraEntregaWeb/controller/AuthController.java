package com.example.PrimeraEntregaWeb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.services.JugadorService;

@RestController
@RequestMapping("/api/iniciar")
public class AuthController {
    @Autowired
    private JugadorService jugadorService;

    @PostMapping("")
    public Jugador iniciarSesion(@RequestBody Map<String, String> credenciales) {
        String usuario = credenciales.get("usuario");
        String contrasena = credenciales.get("contrasena");
        return jugadorService.authenticate(usuario, contrasena);
    }
   
}
