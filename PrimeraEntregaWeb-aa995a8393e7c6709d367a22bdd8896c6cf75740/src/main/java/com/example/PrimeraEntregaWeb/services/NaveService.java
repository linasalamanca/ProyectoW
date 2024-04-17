package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.repository.NaveRepository;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EntityNotFoundException;

@Service
public class NaveService {
    @Autowired
    private NaveRepository naveRepositorio;

    public List<Nave> listarNaves() {
        return naveRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public Nave buscarNave(@NonNull String nombre) {
        return naveRepositorio.findById(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Nave no encontrada con el nombre: " + nombre));
    }

    public Optional<Nave> buscarNaveOptional(String nombre) {
        return naveRepositorio.findById(nombre);
    }

    public void guardarNave(Nave navecita) {
        naveRepositorio.save(navecita);
    }

    public void actualizarNave(Nave navecita) {
        Nave n = naveRepositorio.findById(navecita.getNombre()).orElseThrow();
        // n.setNombre(navecita.getNombre());
        n.setDinero(navecita.getDinero());
        n.setCoordenadaX(navecita.getCoordenadaX());
        n.setCoordenadaY(navecita.getCoordenadaY());
        n.setCoordenadaZ(navecita.getCoordenadaZ());
        n.setVelocidad(navecita.getVelocidad());

        naveRepositorio.save(n);
    }

    public void eliminarNave(String navecita) {
        naveRepositorio.deleteById(navecita);
    }

    // Lista de jugadores
    public List<Jugador> listarEquipo() {
        return naveRepositorio.findEquipo();
    }
     public void actualizarCoordenadasNave(Double x, Double y, Double z, Nave nave) {
         nave.setCoordenadaX(x);
         nave.setCoordenadaY(y);
         nave.setCoordenadaZ(z);
         naveRepositorio.save(nave);   
     }
    
}
