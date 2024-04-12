package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.services.InventarioNaveService;
import com.example.PrimeraEntregaWeb.services.InventarioPlanetaService;

@RestController
@RequestMapping("/api/comprar")
public class ComprarProductoController {

    @Autowired
    private InventarioPlanetaService inventarioPlanetaService;

    // http://localhost:8080/api/comprar/list
    @GetMapping("/list")
    public List<InformacionCompraProductoDTO> listarProductos() {
        return inventarioPlanetaService.listarInformacionCompraProducto();
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
