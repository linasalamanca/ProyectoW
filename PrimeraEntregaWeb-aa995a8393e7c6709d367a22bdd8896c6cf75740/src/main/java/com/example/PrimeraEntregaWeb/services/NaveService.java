package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return naveRepositorio.findById(nombre).orElseThrow(() -> new EntityNotFoundException("Nave no encontrada con el nombre: " + nombre));
    }
    
    public Optional<Nave> buscarNaveOptional(String nombre) {
        return naveRepositorio.findById(nombre);
    }
    

    public void guardarNave(Nave navecita) {
        naveRepositorio.save(navecita);
    }

    public void eliminarNave(String navecita) {
        naveRepositorio.deleteById(navecita);
    }
}
