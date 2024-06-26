package com.example.PrimeraEntregaWeb.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.services.EstrellaService;
import com.example.PrimeraEntregaWeb.services.NaveService;
import com.example.PrimeraEntregaWeb.services.PartidaService;
import com.example.PrimeraEntregaWeb.services.PlanetaService;

import jakarta.persistence.PostLoad;

@RestController
@RequestMapping("/api/escoger-estrella")
@CrossOrigin(origins = "http://localhost:4200")
public class EscogerEstrellaController {

    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private EstrellaService estrellaService;

    @Autowired
    private NaveService naveService;

    @Autowired
    private PartidaService partidaService;

    // http://localhost:8080/api/escoger-estrella/list
    @RequestMapping("/list")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Estrella> listarEstrellas() {
        Double x = naveService.buscarNaveOptional("nave0").get().getCoordenadaX();
        Double y = naveService.buscarNaveOptional("nave0").get().getCoordenadaY();
        Double z = naveService.buscarNaveOptional("nave0").get().getCoordenadaZ();
        return estrellaService.listarEstrellasCercanas(x, y, z);
    }

    //@Secured({"PILOTO", "CAPITAN"})
    @PostMapping("/cambiar-coordenadas-nave/{estrellaId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void cambiarCoordenadasNave(@PathVariable Long estrellaId) {
        Estrella estrella = estrellaService.buscar(estrellaId);
        Nave nave = naveService.buscarNave("nave0");
        naveService.actualizarCoordenadasNave(estrella.getCoordenadaX(), estrella.getCoordenadaY(),
                estrella.getCoordenadaZ(), nave);

    }

    //@Secured({"ROLE_PILOTO", "ROLE_CAPITAN"})
    @PostMapping("/calcular-tiempo/{estrellaId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public boolean calcularTiempo(@PathVariable Long estrellaId) {

        Estrella estrella = estrellaService.buscar(estrellaId);
        Nave nave = naveService.buscarNave("nave0");

        Double distancia = Math.sqrt(Math.pow(estrella.getCoordenadaX() - nave.getCoordenadaX(), 2)
                + Math.pow(estrella.getCoordenadaY() - nave.getCoordenadaY(), 2)
                + Math.pow(estrella.getCoordenadaZ() - nave.getCoordenadaZ(), 2));

        Double tiempo = (distancia / nave.getVelocidad()) + partidaService.obtenerTiempoPartida((long) 1);
        partidaService.actualizarTiempoPartida(tiempo, partidaService.buscar((long) 1));

        log.info("timepo en partida: " + partidaService.buscar((long) 1).getTiempo());
        log.info("tiempo: " + tiempo);
        return partidaService.CalcularTiempo(tiempo, partidaService.buscar((long) 1).getTiempoMax());
    }

    @GetMapping("/tiempo")
    @CrossOrigin(origins = "http://localhost:4200")
    public Double obtenerTiempo() {
        return partidaService.obtenerTiempoPartida((long) 1);
    }
}
