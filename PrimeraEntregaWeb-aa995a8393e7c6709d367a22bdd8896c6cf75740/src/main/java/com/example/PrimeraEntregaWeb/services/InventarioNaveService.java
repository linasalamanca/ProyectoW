package com.example.PrimeraEntregaWeb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
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

    public void actualizarInventario(InventarioNave inventario) {
        InventarioNave in = inventarioNaveRepositorio.findById(inventario.getId()).orElseThrow();
        in.setCantidad(inventario.getCantidad());
        inventarioNaveRepositorio.save(in);
    }

    public void guardarInventario(InventarioNave inventario) {
        inventarioNaveRepositorio.save(inventario);
    }

    public void eliminarInventario(Long id) {
        inventarioNaveRepositorio.deleteById(id);
    }

    /*
     * public Page<InformacionVentaProductoDTO> buscarProducto(String nombre,
     * org.springframework.data.domain.Pageable pageable) {
     * return
     * inventarioNaveRepositorio.findAllByNombreStartingWithIgnoreCase(nombre,
     * pageable);
     * }
     */

    public List<InformacionVentaProductoDTO> listarInformacionVentaProducto() {
        List<InformacionVentaProductoDTO> listaProductosDTO = new ArrayList<>();
        InformacionVentaProductoDTO producto1 = new InformacionVentaProductoDTO("producto1", 2.5, 3.5);
        InformacionVentaProductoDTO producto2 = new InformacionVentaProductoDTO("producto2", 2.1, 3.9);

        listaProductosDTO.add(producto1);
        listaProductosDTO.add(producto2);

        return listaProductosDTO;

    }
}
