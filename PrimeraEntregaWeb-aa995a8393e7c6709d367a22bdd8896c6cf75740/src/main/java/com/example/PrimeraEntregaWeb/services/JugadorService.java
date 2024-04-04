package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

}
