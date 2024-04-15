package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;

@RestController
@RequestMapping("/api/escoger-planeta")
public class ComprarProductoController {

    @Autowired
    private InventarioPlanetaService inventarioPlanetaService;

    // http://localhost:8080/api/comprar/list
    @GetMapping("/{id}/comprar/list")
    public List<InformacionCompraProductoDTO> listarProductos(@PathVariable Long id) {
        return inventarioPlanetaService.listarInformacionCompraProducto(id);
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
