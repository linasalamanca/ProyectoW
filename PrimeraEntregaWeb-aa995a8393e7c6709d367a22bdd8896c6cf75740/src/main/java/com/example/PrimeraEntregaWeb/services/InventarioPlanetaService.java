package com.example.PrimeraEntregaWeb.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.repository.InventarioPlanetaRepository;

import io.micrometer.common.lang.NonNull;

@Service
public class InventarioPlanetaService {

    @Autowired
    private InventarioPlanetaRepository inventarioPlanetaRepositorio;
    private InventarioPlaneta ip;

    public List<InventarioPlaneta> listarInventarioPlaneta() {
        return inventarioPlanetaRepositorio.findAll();
    }

    @SuppressWarnings("null")
    public InventarioPlaneta buscarInventario(@NonNull Long id) {
        return inventarioPlanetaRepositorio.findById(id).orElseThrow();
    }

    /*
     * public Optional<Estrella> buscarEstrellaOptional(Long id) {
     * return inventarioPlanetaRepositorio.findById(id);
     * }
     */
    public void actualizarInventario(InventarioPlaneta inventario) {
        InventarioPlaneta ip = inventarioPlanetaRepositorio.findById(inventario.getId()).orElseThrow();
        ip.setCantidad(inventario.getCantidad());
        ip.setfOfertaDemanda(inventario.getfOfertaDemanda());
        inventarioPlanetaRepositorio.save(ip);
    }

    public void guardarInventario(InventarioPlaneta inventario) {
        inventarioPlanetaRepositorio.save(inventario);
    }

    public void eliminarInventario(Long id) {
        inventarioPlanetaRepositorio.deleteById(id);
    }

    public List<InformacionCompraProductoDTO> listarInformacionCompraProducto() {
        List<InformacionCompraProductoDTO> listaProductosDTO = new ArrayList<>();
        // FALTA PASAR LO QUE RECIBE LA QUERY A LA LISTA
        List<InventarioPlaneta> list = inventarioPlanetaRepositorio.buscarProductos(this.ip.getId());
        for (InventarioPlaneta i : list) {
            InformacionCompraProductoDTO compra = new InformacionCompraProductoDTO(i.getProducto().getTipo(),
                    i.getCantidad(), i.getfOfertaDemanda());
            listaProductosDTO.add(compra);
        }
        return listaProductosDTO;

    }
}
