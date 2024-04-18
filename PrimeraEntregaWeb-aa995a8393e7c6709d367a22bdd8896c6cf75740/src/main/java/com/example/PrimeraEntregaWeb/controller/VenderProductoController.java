package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.services.InventarioNaveService;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;
import com.example.PrimeraEntregaWeb.services.PartidaService;
import com.example.PrimeraEntregaWeb.services.PlanetaService;

@RestController
@RequestMapping("/api/vender")
public class VenderProductoController {

    @Autowired
    private InventarioNaveService inventarioNaveService;
    @Autowired
    private PartidaService partidaService;
    @Autowired
    private InventarioPlanetaService inventarioPlanetaService;

    private InventarioPlaneta inventarioPlaneta;

    @Autowired
    private PlanetaService planetaService;

    // http://localhost:8080/api/vender/list
    @GetMapping("/list/{id}")
    public List<InformacionVentaProductoDTO> listarProductos(@PathVariable Long id) {
        return inventarioNaveService.listarInformacionVentaProducto("nave0");
    }

    @PostMapping("/realizar-venta/{id}")
    public void realizarVenta(@PathVariable Long id) {
       
        inventarioPlaneta = new InventarioPlaneta(inventarioNaveService.buscarInventario(id).getCantidad(),
                inventarioNaveService.buscarInventario(id).getfOfertaDemanda());
        inventarioPlaneta.setProducto(inventarioNaveService.buscarInventario(id).getProducto());
        inventarioPlaneta.setPlaneta(planetaService.buscarPlaneta(id));
        planetaService.crearInventario(inventarioPlaneta, planetaService.buscarPlaneta(id));
        
        inventarioPlanetaService.cambiarCantidadInventario(
                inventarioPlanetaService.buscarInventario(id).getCantidad() - 1,
                inventarioPlanetaService.buscarInventario(id));
    }

    @PostMapping("/actualizar-puntaje/{id}")
    public void actualizarPuntaje(@PathVariable Long id) {
        Double puntaje = partidaService.buscar((long) 1).getPuntaje()
        + inventarioNaveService.buscarInventario(id).getProducto().getPrecio();
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
