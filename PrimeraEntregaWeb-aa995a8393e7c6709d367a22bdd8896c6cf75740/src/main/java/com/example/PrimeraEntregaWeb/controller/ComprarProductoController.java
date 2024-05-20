package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.services.InventarioNaveService;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;
import com.example.PrimeraEntregaWeb.services.NaveService;
import com.example.PrimeraEntregaWeb.services.PartidaService;
import com.example.PrimeraEntregaWeb.services.JugadorService;

import jakarta.servlet.http.HttpSession;

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

    @Autowired
    private JugadorService jugadorService;

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
        if(inventarioPlanetaService.buscarInventario(id).getCantidad() - 1 < 0){
            inventarioPlanetaService.eliminarInventario(id);
        }else{
            inventarioPlanetaService.cambiarCantidadInventario(
                inventarioPlanetaService.buscarInventario(id).getCantidad() - 1,
                inventarioPlanetaService.buscarInventario(id));
        }    
    }

    /*@PostMapping("/realizar-compra/{id}")
public void realizarCompra(@PathVariable Long id, HttpSession session) {
    // Obtener el usuario de la sesión
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        throw new RuntimeException("Usuario no autenticado");
    }

    // Buscar el jugador por usuario
    Jugador jugador = jugadorService.buscarJugadorPorUsuario(usuario);
    if (jugador == null) {
        throw new RuntimeException("Jugador no encontrado");
    }

    // Buscar la nave del jugador
    Nave nave = naveService.buscarNavePorUsuario(usuario);
    if (nave == null) {
        throw new RuntimeException("Nave no encontrada para el usuario: " + usuario);
    }

    // Calcular la capacidad máxima y el volumen actual de la nave
    Double capacidadMaxima = nave.getCapacidadMax();
    Double volumenActual = inventarioNaveService.calcularVolumenTotal(nave.getInventario());

    // Buscar el inventario del planeta
    InventarioPlaneta inventarioPlaneta = inventarioPlanetaService.buscarInventario(id);

    // Crear el nuevo inventario de la nave
    InventarioNave inventarioNave = new InventarioNave(inventarioPlaneta.getCantidad(), inventarioPlaneta.getfOfertaDemanda());
    inventarioNave.setProducto(inventarioPlaneta.getProducto());
    inventarioNave.setNave(nave);

    // Guardar el nuevo inventario en la nave
    if(volumenActual + inventarioPlaneta.getProducto().getVolumen() > capacidadMaxima){
        throw new RuntimeException("No hay suficiente espacio en la nave");
    }else{
        naveService.crearInventario(inventarioNave, nave);

        // Actualizar o eliminar el inventario del planeta
        if (inventarioPlaneta.getCantidad() - 1 < 0) {
            inventarioPlanetaService.eliminarInventario(id);
        } else {
            inventarioPlanetaService.cambiarCantidadInventario(inventarioPlaneta.getCantidad() - 1, inventarioPlaneta);
        }
    }
    
}*/


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
