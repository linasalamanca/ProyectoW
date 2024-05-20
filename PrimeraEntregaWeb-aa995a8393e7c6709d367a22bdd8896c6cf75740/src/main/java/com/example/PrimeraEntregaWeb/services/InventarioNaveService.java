package com.example.PrimeraEntregaWeb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.repository.InventarioNaveRepository;
import io.micrometer.common.lang.NonNull;

@Service
public class InventarioNaveService {

    @Autowired
    private InventarioNaveRepository inventarioNaveRepositorio;
    private InventarioNave in;

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

    public void cambiarCantidadInventario(Double cantidad, InventarioNave in) {
        in.setCantidad(cantidad);
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

    public List<InformacionVentaProductoDTO> listarInformacionVentaProducto(String nombre) {
        List<InformacionVentaProductoDTO> listaProductosDTO = new ArrayList<>();

        List<InventarioNave> list = inventarioNaveRepositorio.buscarProductosPorNombreNave(nombre);

        for (InventarioNave i : list) {

            Double precio = i.getfOfertaDemanda() / (1 + i.getCantidad());
            i.getProducto().setPrecio(precio);

            InformacionVentaProductoDTO venta = new InformacionVentaProductoDTO(i.getProducto().getTipo(),
                    i.getCantidad(), i.getfOfertaDemanda(), precio, i.getId());
            listaProductosDTO.add(venta);
        }
        return listaProductosDTO;

    }

    public Double calcularVolumenTotal(List<InventarioNave> list) {
        Double vol = 0.0;
        List<InventarioNave> productos = inventarioNaveRepositorio.buscarProductosPorNombreNave("nave0");

        for (InventarioNave p : productos) {
            vol += p.getProducto().getVolumen();
        }

        return vol;
    }
}
