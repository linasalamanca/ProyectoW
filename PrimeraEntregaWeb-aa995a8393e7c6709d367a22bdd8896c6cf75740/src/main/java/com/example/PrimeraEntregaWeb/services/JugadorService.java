package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.repository.JugadorRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepositorio;

    public List<Jugador> listarJugadores() {
        return jugadorRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public Jugador buscarJugador(@NonNull Long id) {
        return jugadorRepositorio.findById(id).orElseThrow();
    }

    public void actualizarJuagdor(Jugador jugador) {
        Jugador j = jugadorRepositorio.findById(jugador.getId()).orElseThrow();
        j.setRol(jugador.getRol());
        j.setUsuario(jugador.getUsuario());
        j.setContrasena(jugador.getContrasena());

        jugadorRepositorio.save(j);
    }

    public void guardarJugador(Jugador jugador) {
        jugadorRepositorio.save(jugador);
    }

    public void eliminarJugador(Long id) {
        jugadorRepositorio.deleteById(id);
    }

    public Jugador authenticate(String usuario, String contrasena) {
        Jugador jugador = jugadorRepositorio.findByUsuario(usuario)
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        if (jugador != null && jugador.getContrasena().equals(contrasena)) {
            return jugador;
        }
        return null;
    }

    public Jugador buscarJugadorPorUsuario(String usuario) {
        return jugadorRepositorio.findByUsuario(usuario)
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
    }

     public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return jugadorRepositorio.findByUsuario(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

}
