package com.example.PrimeraEntregaWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.services.EstrellaService;

@RestController
@RequestMapping("/api/escoger-estrella")
public class EscogerEstrellaController {
    @Autowired
    private EstrellaService estrellaService;

    // http://localhost:8080/api/escoger-estrella/list
    @RequestMapping("/list")
    public List<Estrella> listarEstrellas() {
       // Pageable pageable = PageRequest.of(0, 10);
        return estrellaService.listarEstrellasCercanas();
    }
}
