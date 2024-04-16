package com.example.PrimeraEntregaWeb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.services.EstrellaService;
import com.example.PrimeraEntregaWeb.services.NaveService;
import com.example.PrimeraEntregaWeb.services.PlanetaService;

import jakarta.persistence.PostLoad;

@RestController
@RequestMapping("/api/escoger-estrella")
public class EscogerEstrellaController {
    @Autowired
    private EstrellaService estrellaService;

    @Autowired
    private NaveService naveService;
    // http://localhost:8080/api/escoger-estrella/list
    @RequestMapping("/list")
    public List<Estrella> listarEstrellas() {
        Double x = naveService.buscarNaveOptional("nave0").get().getCoordenadaX();
        Double y = naveService.buscarNaveOptional("nave0").get().getCoordenadaY();
        Double z = naveService.buscarNaveOptional("nave0").get().getCoordenadaZ();
        return estrellaService.listarEstrellasCercanas(x, y, z);
    }

    @PostMapping("/cambiar-coordenadas-nave/{estrellaId}")
    public void cambiarCoordenadasNave(@PathVariable Long estrellaId){
        Estrella estrella = estrellaService.buscar(estrellaId);
        Nave nave = naveService.buscarNave("nave0");
        naveService.actualizarCoordenadasNave(estrella.getCoordenadaX(), estrella.getCoordenadaY(), estrella.getCoordenadaZ(), nave);

    }
}
