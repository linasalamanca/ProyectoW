package com.example.PrimeraEntregaWeb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.repository.InventarioNaveRepository;
import io.micrometer.common.lang.NonNull;

@Service
public class InventarioNaveService {
    @Autowired
    private InventarioNaveRepository inventarioNaveRepositorio;

    public List<InventarioNave> listarInventarioNave() {
        return inventarioNaveRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public InventarioNave buscarInventario(@NonNull Long id) {
        return inventarioNaveRepositorio.findById(id).orElseThrow();
    }

    /*
     * public Optional<Estrella> buscarEstrellaOptional(Long id) {
     * return inventarioNaveRepositorio.findById(id);
     * }
     */
    public void actualizarInventario(InventarioNave inventario) {
        // inventarioNaveRepositorio.save(inventario);
        InventarioNave in = inventarioNaveRepositorio.findById(inventario.getId()).orElseThrow();
        // n.setNombre(navecita.getNombre());
        in.setCantidad(inventario.getCantidad());
        inventarioNaveRepositorio.save(in);
    }

    public void guardarInventario(InventarioNave inventario) {
        inventarioNaveRepositorio.save(inventario);
    }

    public void eliminarInventario(Long id) {
        inventarioNaveRepositorio.deleteById(id);
    }
}
