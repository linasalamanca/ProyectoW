package com.example.PrimeraEntregaWeb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Partida;
import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.repository.InventarioNaveRepository;
import com.example.PrimeraEntregaWeb.repository.InventarioPlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.JugadorRepository;
import com.example.PrimeraEntregaWeb.repository.PartidaRepository;

import io.micrometer.common.lang.NonNull;
import jakarta.transaction.Transactional;

@Service
public class InventarioNaveService {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private InventarioNaveRepository inventarioNaveRepositorio;

    @Autowired
    private JugadorRepository jugadorRepositorio;

    @Autowired
    private PartidaRepository partidaRepositorio;

    @Autowired
    private InventarioPlanetaRepository inventarioPlanetaRepositorio;

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

    @Transactional
    public void realizarCompra(Long idInventario, Long idJugador) {
        InventarioPlaneta inventarioPlaneta = inventarioPlanetaRepositorio.findById(idInventario)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        Jugador jugador = jugadorRepositorio.findById(idJugador)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        double precio = inventarioPlaneta.getProducto().getPrecio();
        if (jugador.getNave().getDinero() >= precio) {
            // Verificar si la nave tiene capacidad suficiente
            double capacidadUsada = jugador.getNave().getInventario().stream().mapToDouble(InventarioNave::getCantidad)
                    .sum();
            double capacidadProducto = inventarioPlaneta.getProducto().getVolumen();
            log.info("Capacidad usada: " + capacidadUsada + " Capacidad producto: " + capacidadProducto);
            log.info("capacidad maxima nave: " + jugador.getNave().getCapacidadMax());

            if (jugador.getNave().getCapacidadMax() >= capacidadUsada + capacidadProducto) {
                // Actualizar el dinero de la nave
                jugador.getNave().setDinero(jugador.getNave().getDinero() - precio);

                // Actualizar la cantidad en el inventario del planeta
                if (inventarioPlaneta.getCantidad() > 0) {
                    inventarioPlaneta.setCantidad(inventarioPlaneta.getCantidad() - 1);
                } else {
                    throw new RuntimeException("Producto agotado en el planeta");
                }

                // Añadir el producto al inventario de la nave
                Optional<InventarioNave> inventarioNaveOpt = jugador.getNave().getInventario().stream()
                        .filter(invNave -> invNave.getProducto().getId()
                                .equals(inventarioPlaneta.getProducto().getId()))
                        .findFirst();

                if (inventarioNaveOpt.isPresent()) {
                    InventarioNave inventarioNave = inventarioNaveOpt.get();
                    inventarioNave.setCantidad(inventarioNave.getCantidad() + capacidadProducto);
                    inventarioNaveRepositorio.save(inventarioNave);
                } else {
                    InventarioNave nuevoInventarioNave = new InventarioNave();
                    nuevoInventarioNave.setCantidad(1.0);
                    nuevoInventarioNave.setfOfertaDemanda(inventarioPlaneta.getfOfertaDemanda());
                    nuevoInventarioNave.setNave(jugador.getNave());
                    nuevoInventarioNave.setProducto(inventarioPlaneta.getProducto());
                    inventarioNaveRepositorio.save(nuevoInventarioNave);
                }

                inventarioPlanetaRepositorio.save(inventarioPlaneta);
                jugadorRepositorio.save(jugador);
            } else {
                throw new RuntimeException("Capacidad insuficiente en la nave");
            }
        } else {
            throw new RuntimeException("Dinero insuficiente");
        }
    }

    @Transactional
    public void realizarVenta(Long idInventario, Long idJugador) {
        InventarioPlaneta inventarioPlaneta = inventarioPlanetaRepositorio.findById(idInventario)
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        Jugador jugador = jugadorRepositorio.findById(idJugador)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));

        // Verificar si la nave tiene el producto en su inventario
        Optional<InventarioNave> inventarioNaveOpt = jugador.getNave().getInventario().stream()
                .filter(invNave -> invNave.getProducto().getId().equals(inventarioPlaneta.getProducto().getId()))
                .findFirst();

        if (inventarioNaveOpt.isPresent()) {
            InventarioNave inventarioNave = inventarioNaveOpt.get();

            // Verificar si la cantidad es suficiente para vender
            if (inventarioNave.getCantidad() > 0) {
                double precio = inventarioPlaneta.getProducto().getPrecio();

                // Actualizar el dinero de la nave
                jugador.getNave().setDinero(jugador.getNave().getDinero() + precio);

                // Actualizar la cantidad en el inventario de la nave
                inventarioNave.setCantidad(inventarioNave.getCantidad() - 1);
                inventarioNaveRepositorio.save(inventarioNave);

                // Actualizar la cantidad en el inventario del planeta
                inventarioPlaneta.setCantidad(inventarioPlaneta.getCantidad() + 1);
                inventarioPlanetaRepositorio.save(inventarioPlaneta);

                // Si la cantidad del producto en la nave llega a 0, eliminarlo del inventario
                // de la nave
                if (inventarioNave.getCantidad() == 0) {
                    jugador.getNave().getInventario().remove(inventarioNave);
                    inventarioNaveRepositorio.delete(inventarioNave);
                }

                jugadorRepositorio.save(jugador);
            } else {
                throw new RuntimeException("Cantidad insuficiente en la nave para vender");
            }
        } else {
            throw new RuntimeException("Producto no encontrado en el inventario de la nave");
        }
    }

}
