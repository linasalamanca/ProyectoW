package com.example.PrimeraEntregaWeb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.services.EstrellaService;

@RestController
@RequestMapping("api/escoger-planeta")
public class EscogerPlanetaController {

    @Autowired
    private EstrellaService estrellaService;

    @GetMapping("/list/{id}")
    public List<Planeta> listarPlanetas(@PathVariable Long id) {
        return estrellaService.listarPlanetasPorEstrellas(id);
    }
}
