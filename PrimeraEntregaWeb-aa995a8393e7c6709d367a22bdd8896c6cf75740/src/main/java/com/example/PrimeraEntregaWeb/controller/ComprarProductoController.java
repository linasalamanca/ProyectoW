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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.dto.CompraDTO;
import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Partida;
import com.example.PrimeraEntregaWeb.services.InventarioNaveService;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;
import com.example.PrimeraEntregaWeb.services.NaveService;
import com.example.PrimeraEntregaWeb.services.PartidaService;
import com.example.PrimeraEntregaWeb.services.JugadorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/comprar")
public class ComprarProductoController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private InventarioPlanetaService inventarioPlanetaService;

    @Autowired
    private InventarioNaveService inventarioNaveService;

    @Autowired
    private PartidaService partidaService;

    @Autowired
    private NaveService naveService;

    @Autowired
    private JugadorService jugadorService;

    private InventarioNave inventarioNave;

    // http://localhost:8080/api/comprar/list
    @GetMapping("/list/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<InformacionCompraProductoDTO> listarProductos(@PathVariable Long id) {
        return inventarioPlanetaService.listarInformacionCompraProducto(id);
    }

    // @PostMapping("/realizar-compra/{id}")
    /*
     * public void realizarCompra(@PathVariable Long id) {
     * Double capacidadMaxima = naveService.buscarNave("nave0").getCapacidadMax();
     * 
     * Double volumenActual = inventarioNaveService
     * .calcularVolumenTotal(naveService.buscarNave("nave0").getInventario());
     * 
     * inventarioNave = new
     * InventarioNave(inventarioPlanetaService.buscarInventario(id).getCantidad(),
     * inventarioPlanetaService.buscarInventario(id).getfOfertaDemanda());
     * inventarioNave.setProducto(inventarioPlanetaService.buscarInventario(id).
     * getProducto());
     * inventarioNave.setNave(naveService.buscarNave("nave0"));
     * naveService.crearInventario(inventarioNave, naveService.buscarNave("nave0"));
     * if(inventarioPlanetaService.buscarInventario(id).getCantidad() - 1 < 0){
     * inventarioPlanetaService.eliminarInventario(id);
     * }else{
     * inventarioPlanetaService.cambiarCantidadInventario(
     * inventarioPlanetaService.buscarInventario(id).getCantidad() - 1,
     * inventarioPlanetaService.buscarInventario(id));
     * }
     * }
     */
    @PostMapping("/realizar-compra")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> realizarCompra(@RequestBody CompraDTO objeto) {
        try {
            inventarioNaveService.realizarCompra(objeto.getIdInventario(), objeto.getIdJugador());
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
                - inventarioPlanetaService.buscarInventario(idInventario).getProducto().getPrecio();
        log.info("puntaje" + puntaje);
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
