package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.services.InventarioNaveService;

@RestController
@RequestMapping("/api/vender")
public class VenderProductoController {

    @Autowired
    private InventarioNaveService inventarioNaveService;

    // http://localhost:8080/api/vender/list
    @GetMapping("/list")
    public List<InformacionVentaProductoDTO> listarProductos() {
        return inventarioNaveService.listarInformacionVentaProducto();
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
