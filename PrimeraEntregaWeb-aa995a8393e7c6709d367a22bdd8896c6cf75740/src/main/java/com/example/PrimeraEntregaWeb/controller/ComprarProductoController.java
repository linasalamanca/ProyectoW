package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.services.InventarioNaveService;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;
import com.example.PrimeraEntregaWeb.services.NaveService;
import com.example.PrimeraEntregaWeb.services.PartidaService;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/comprar")
public class ComprarProductoController {

    @Autowired
    private InventarioPlanetaService inventarioPlanetaService;

    @Autowired
    private InventarioNaveService inventarioNaveService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private NaveService naveService;

    private InventarioNave inventarioNave;

    // http://localhost:8080/api/comprar/list
    @GetMapping("/list/{id}")
    public List<InformacionCompraProductoDTO> listarProductos(@PathVariable Long id) {
        return inventarioPlanetaService.listarInformacionCompraProducto(id);
    }

    @PostMapping("/realizar-compra/{id}")
    public void realizarCompra(@PathVariable Long id) {
        Double capacidadMaxima = naveService.buscarNave("nave0").getCapacidadMax();

        Double volumenActual = inventarioNaveService
                .calcularVolumenTotal(naveService.buscarNave("nave0").getInventario());

        inventarioNave = new InventarioNave(inventarioPlanetaService.buscarInventario(id).getCantidad(),
                inventarioPlanetaService.buscarInventario(id).getfOfertaDemanda());
        inventarioNave.setProducto(inventarioPlanetaService.buscarInventario(id).getProducto());
        inventarioNave.setNave(naveService.buscarNave("nave0"));
        naveService.crearInventario(inventarioNave, naveService.buscarNave("nave0"));
        inventarioPlanetaService.cambiarCantidadInventario(
                inventarioPlanetaService.buscarInventario(id).getCantidad() - 1,
                inventarioPlanetaService.buscarInventario(id));
    }

    @PostMapping("/actualizar-puntaje/{id}")
    public void actualizarPuntaje(@PathVariable Long id) {
        Double puntaje = partidaService.buscar((long) 1).getPuntaje()
                - inventarioPlanetaService.buscarInventario(id).getProducto().getPrecio();
        partidaService.actualizarPuntaje(puntaje, partidaService.buscar((long) 1));
    }

    @GetMapping("/obtener-puntaje")
    public Double obtenerPuntaje() {
        return partidaService.obtenerPuntajePartida((long) 1);
    }

    // https://www.baeldung.com/spring-rest-openapi-documentation
    /*
     * @GetMapping("/search")
     * public Page<InformacionVentaProductoDTO> buscarProducto(@RequestParam String
     * nombre, Pageable pageable) {
     * return inventarioNaveService.buscarProducto(nombre, pageable);
     * }
     */

}
