package com.example.PrimeraEntregaWeb.init;

import java.util.Arrays;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.repository.EstrellaRepository;
import com.example.PrimeraEntregaWeb.repository.JugadorRepository;
import com.example.PrimeraEntregaWeb.repository.NaveRepository;
import com.example.PrimeraEntregaWeb.repository.PlanetaRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private NaveRepository naveRepository;
    @Autowired
    private EstrellaRepository estrellaRepository;
    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private PlanetaRepository planetaRepository;

    @Override
    public void run(String... args) throws Exception {

        Nave nave1 = new Nave(200, 12.4, 24.6, 67.3, "nave1", 20.0);
        Nave nave2 = new Nave(300, 18.9, 13.3, 11.9, "nave2", 50.0);
        Nave nave3 = new Nave(400, 17.7, 32.8, 17.1, "nave3", 40.0);
        Nave nave4 = new Nave(500, 11.4, 13.6, 13.2, "nave4", 64.0);
        Nave nave5 = new Nave(600, 43.2, 12.1, 12.8, "nave5", 38.0);

        List<Nave> naves = Arrays.asList(nave1, nave2, nave3, nave4, nave5);
        naveRepository.saveAll(naves);

        Estrella estrella1 = new Estrella(76.2, 24.6, 67.3);
        Estrella estrella2 = new Estrella(18.9, 13.3, 11.9);
        Estrella estrella3 = new Estrella(17.7, 32.8, 17.1);
        Estrella estrella4 = new Estrella(11.4, 13.6, 13.2);
        Estrella estrella5 = new Estrella(43.2, 12.1, 12.8);

        List<Estrella> estrellas = Arrays.asList(estrella1, estrella2, estrella3, estrella4, estrella5);
        estrellaRepository.saveAll(estrellas);

        Jugador jugador1 = new Jugador("a", "usuario1", "hola");
        Jugador jugador2 = new Jugador("b", "usuario2", "hola");
        Jugador jugador3 = new Jugador("c", "usuario3", "hola");
        Jugador jugador4 = new Jugador("d", "usuario4", "hola");
        Jugador jugador5 = new Jugador("e", "usuario5", "hola");

        List<Jugador> jugadores = Arrays.asList(jugador1, jugador2, jugador3, jugador4, jugador5);
        jugadorRepository.saveAll(jugadores);

        Planeta planeta1 = new Planeta("planeta1");
        Planeta planeta2 = new Planeta("planeta2");
        Planeta planeta3 = new Planeta("planeta3");
        Planeta planeta4 = new Planeta("planeta4");
        Planeta planeta5 = new Planeta("planeta5");

        List<Planeta> planetas = Arrays.asList(planeta1, planeta2, planeta3, planeta4, planeta5);
        planetaRepository.saveAll(planetas);

    }

}
