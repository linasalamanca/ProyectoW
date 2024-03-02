package com.example.PrimeraEntregaWeb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.repository.ProductoRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepositorio;

    public List<Producto> listarProductos() {
        return productoRepositorio.findAll();
    }

    public Producto buscarProducto(@NonNull Long id) {
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrada con el id: " + id));
    }

    public Optional<Producto> buscarProductoOptional(Long id) {
        return productoRepositorio.findById(id);
    }

    public void guardarProducto(Producto producto) {
        productoRepositorio.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepositorio.deleteById(id);
    }
}
