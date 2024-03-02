package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.repository.EstrellaRepository;

import io.micrometer.common.lang.NonNull;

import com.example.PrimeraEntregaWeb.model.Estrella;

@Service
public class EstrellaService {
    @Autowired
    private EstrellaRepository estrellaRepositorio;
     public List<Estrella> listarEstrellas() {
        return estrellaRepositorio.findAll();
    }
    @SuppressWarnings("null")
    public Estrella buscar(@NonNull Long id) {
        return estrellaRepositorio.findById(id).orElseThrow();
    }
    public void guardarEstrella(Estrella estrellita) {
        estrellaRepositorio.save(estrellita);
    }
    public void eliminarEstrella(Long id) {
        estrellaRepositorio.deleteById(id);
    }

}