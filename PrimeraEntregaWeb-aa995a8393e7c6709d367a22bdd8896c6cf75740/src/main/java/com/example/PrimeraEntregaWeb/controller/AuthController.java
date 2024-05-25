package com.example.PrimeraEntregaWeb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.dto.JwtAuthenticationResponse;
import com.example.PrimeraEntregaWeb.dto.LoginDTO;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.services.AuthService;
import com.example.PrimeraEntregaWeb.services.JugadorService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private AuthService authenticationService;

    /*@PostMapping("")
    public Jugador iniciarSesion(@RequestBody LoginDTO credenciales) {
        String usuario = credenciales.getUser();
        String contrasena = credenciales.getPassword();
        return jugadorService.authenticate(usuario, contrasena);
    }*/

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }
   
}
