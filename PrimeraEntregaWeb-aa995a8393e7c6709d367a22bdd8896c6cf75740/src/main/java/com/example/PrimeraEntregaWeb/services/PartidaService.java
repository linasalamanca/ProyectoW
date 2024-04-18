package com.example.PrimeraEntregaWeb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.model.Partida;
import com.example.PrimeraEntregaWeb.repository.PartidaRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    public Partida buscar(@NonNull Long id) {
        return partidaRepository.findById(id).orElseThrow();
    }

    public void guardarPartida(Partida partida) {
        partidaRepository.save(partida);
    }

    public Double obtenerTiempoPartida(Long id) {
        return partidaRepository.findTiempoPartida(id);
    }

    public Double obtenerPuntajePartida(Long id) {
        return partidaRepository.findPuntajePartida(id);
    }

    public void actualizarTiempoPartida(Double tiempo, Partida partida) {
        partida.setTiempo(tiempo);
        partidaRepository.save(partida);
    }

    public boolean CalcularTiempo(Double tiempo, Double tiempoMax) {
        if (tiempo > tiempoMax) {
            return true;
        }
        return false;
    }

    public void actualizarPuntaje(Double puntaje, Partida partida) {
        partida.setPuntaje(puntaje);
        partidaRepository.save(partida);
    }
}
