package com.example.PrimeraEntregaWeb.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.PrimeraEntregaWeb.dto.JwtAuthenticationResponse;
import com.example.PrimeraEntregaWeb.dto.LoginDTO;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.repository.JugadorRepository;

@Service
public class AuthService {
     private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

   /* public JwtAuthenticationResponse signup(UserRegistrationDTO request) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER);

        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt, user.getEmail(), user.getRole());
    }*/

    public JwtAuthenticationResponse login(LoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
        Jugador jugador = jugadorRepository.findByUsuario(request.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user or password."));
        String jwt = jwtService.generateToken(jugador);
        return new JwtAuthenticationResponse(jwt, jugador.getId(),jugador.getUsuario(), jugador.getRol());
    }
    
}
