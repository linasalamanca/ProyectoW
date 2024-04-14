package com.example.PrimeraEntregaWeb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.services.EstrellaService;

@RestController
@RequestMapping("api/estrellas/{id}")
public class EscogerPlanetaController {
    @Autowired
    private EstrellaService estrellaService;

    @RequestMapping("/planetas")
    public List<Planeta> listarPlanetas() {
        return estrellaService.listarPlanetasPorEstrellas();
    }

}