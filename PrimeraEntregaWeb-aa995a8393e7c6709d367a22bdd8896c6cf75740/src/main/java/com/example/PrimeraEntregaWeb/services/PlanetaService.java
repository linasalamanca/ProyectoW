package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.repository.PlanetaRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PlanetaService {

    @Autowired
    private PlanetaRepository planetaRepositorio;

    public List<Planeta> listarPlanetas() {
        return planetaRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public Planeta buscarPlaneta(@NonNull Long id) {
        return planetaRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Planeta no encontrada con el nombre: " + id));
    }

    public Optional<Planeta> buscarPlanetaOptional(Long id) {
        return planetaRepositorio.findById(id);
    }

    public void guardarPlaneta(Planeta planeta) {

        planetaRepositorio.save(planeta);
    }

    public void actualizarPlaneta(Planeta planeta) {
        Planeta p = planetaRepositorio.findById(planeta.getId()).orElseThrow();
        p.setNombre(planeta.getNombre());
        planetaRepositorio.save(p);
    }

    public void eliminarPlaneta(Long id) {
        planetaRepositorio.deleteById(id);
    }
}
