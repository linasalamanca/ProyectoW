package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.dto.CompraDTO;
import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Partida;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.services.InventarioNaveService;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;
import com.example.PrimeraEntregaWeb.services.JugadorService;
import com.example.PrimeraEntregaWeb.services.PartidaService;
import com.example.PrimeraEntregaWeb.services.PlanetaService;

@RestController
@RequestMapping("/api/vender")
public class VenderProductoController {

    
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private InventarioNaveService inventarioNaveService;
    @Autowired
    private PartidaService partidaService;
    @Autowired
    private InventarioPlanetaService inventarioPlanetaService;

    private InventarioPlaneta inventarioPlaneta;

    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private JugadorService jugadorService;
    // http://localhost:8080/api/vender/list
    @GetMapping("/list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<InformacionVentaProductoDTO> listarProductos(@PathVariable Long id) {
        String nave = jugadorService.buscarJugador(id).getNave().getNombre();
        return inventarioNaveService.listarInformacionVentaProducto(nave);
    }

    /*@PostMapping("/realizar-venta/{id}")
    public void realizarVenta(@PathVariable Long id) {

        inventarioPlaneta = new InventarioPlaneta(inventarioNaveService.buscarInventario(id).getCantidad(),
                inventarioNaveService.buscarInventario(id).getfOfertaDemanda());
        inventarioPlaneta.setProducto(inventarioNaveService.buscarInventario(id).getProducto());
        inventarioPlaneta.setPlaneta(planetaService.buscarPlaneta(id));
        planetaService.crearInventario(inventarioPlaneta, planetaService.buscarPlaneta(id));
        
        if (inventarioNaveService.buscarInventario(id).getCantidad() - 1 < 0) {
            inventarioNaveService.eliminarInventario(id);
        } else {
            inventarioNaveService.cambiarCantidadInventario(
                    inventarioNaveService.buscarInventario(id).getCantidad() - 1,
                    inventarioNaveService.buscarInventario(id));
            
        }
    }*/
    //@Secured({"COMERCIANTE", "CAPITAN"})
    @PostMapping("/realizar-venta")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> realizarVenta(@RequestBody CompraDTO objeto) {
        try {
            inventarioNaveService.realizarVenta(objeto.getIdInventario(), objeto.getIdJugador());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Error al realizar la compra: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al realizar la compra: " + e.getMessage());
        }
    }

    @PatchMapping("/actualizar-puntaje/{idJugador}/{idInventario}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void actualizarPuntaje(@PathVariable Long idJugador, @PathVariable Long idInventario) {
        // Buscar el jugador y su nave asociada
        Jugador jugador = jugadorService.buscarJugador(idJugador);
        Nave nave = jugador.getNave();

        // Buscar la partida asociada a la nave
        Partida partida = nave.getPartida();

        // Calcular el nuevo puntaje basado en el precio del producto
        Double puntaje = partida.getPuntaje() 
        + inventarioNaveService.buscarInventario(idInventario).getProducto().getPrecio();
        log.info("puntaje" + puntaje);
        log.info( "precio inventario" + inventarioNaveService.buscarInventario(idInventario).getProducto().getPrecio());
        log.info("id inventario"+ idInventario);
        log.info("Inventario existe"+ inventarioNaveService.buscarInventario(idInventario));
        log.info("puntaje partida" + partida.getPuntaje());
        // Actualizar el puntaje de la partida
        partidaService.actualizarPuntaje(puntaje, partida);
    }

    @GetMapping("/obtener-puntaje/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Double obtenerPuntaje(@PathVariable Long id) {
        Jugador jugador = jugadorService.buscarJugador(id);
        return partidaService.obtenerPuntajePartida(jugador.getNave().getPartida().getId());
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
