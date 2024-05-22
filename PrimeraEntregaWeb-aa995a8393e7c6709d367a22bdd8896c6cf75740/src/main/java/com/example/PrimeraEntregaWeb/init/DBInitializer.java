package com.example.PrimeraEntregaWeb.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Partida;
import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.model.TipoNave;
import com.example.PrimeraEntregaWeb.repository.EstrellaRepository;
import com.example.PrimeraEntregaWeb.repository.InventarioNaveRepository;
import com.example.PrimeraEntregaWeb.repository.InventarioPlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.JugadorRepository;
import com.example.PrimeraEntregaWeb.repository.NaveRepository;
import com.example.PrimeraEntregaWeb.repository.PartidaRepository;
import com.example.PrimeraEntregaWeb.repository.PlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.ProductoRepository;
import com.example.PrimeraEntregaWeb.repository.TipoNaveRepository;

@Profile("default")
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
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private TipoNaveRepository tipoNaveRepository;
    @Autowired
    private InventarioNaveRepository inventarioNaveRepository;
    @Autowired
    private InventarioPlanetaRepository inventarioPlanetaRepository;
    @Autowired
    private PartidaRepository partidaRepository;

    @Override
    public void run(String... args) throws Exception {

        List<TipoNave> tipoNaves = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            TipoNave tipoNave = new TipoNave("tipoNave" + i, random.nextDouble(),
                    random.nextDouble());
            tipoNaves.add(tipoNave);
        }
        tipoNaveRepository.saveAll(tipoNaves);

        List<Producto> productos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            productos.add(new Producto((double) i + 1, "producto-prueba" + i));
            productos.get(i).setPrecio(50.0);
        }
        productoRepository.saveAll(productos);

        List<Nave> naves = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Nave nave = new Nave(1000.00, random.nextDouble() * (90 - 10) + 10,
                    random.nextDouble() * (90 - 10) + 10,
                    random.nextDouble() * (90 - 10) + 10, "nave" + i, random.nextDouble() * 200 +
                            15,
                    random.nextDouble() * 500.5);
            nave.setTipo(tipoNaves.get(i));
            naves.add(nave);
        }
        naveRepository.saveAll(naves);

        for (Nave nave : naves) {
            for (int k = 0; k < productos.size(); k++) {
                InventarioNave inventarioNave = new InventarioNave();
                inventarioNave.setNave(nave);
                inventarioNave.setCantidad(20.2 + k * 2);
                inventarioNave.setfOfertaDemanda(random.nextDouble() * 1000000);
                inventarioNave.setProducto(productos.get(k));
                inventarioNaveRepository.save(inventarioNave);
            }
        }

        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Jugador jugador = new Jugador("rol" + i, "jugador" + i, "hola" + i);
            jugadores.add(jugador);
        }

        List<List<Jugador>> equipos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Jugador> equipo = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                equipo.add(jugadores.get(i * 3 + j));
            }
            equipos.add(equipo);
        }

        for (int i = 0; i < 3; i++) {
            List<Jugador> equipo = equipos.get(i);
            Nave nave = naves.get(i);
            for (Jugador jugador : equipo) {
                jugador.setNave(nave);
                nave.addJugador(jugador);
            }
        }

        jugadorRepository.saveAll(jugadores);

        for (int i = 0; i < 5; i++) {
            Estrella estrella = new Estrella(random.nextDouble() * (90 - 10) + 10,
                    random.nextDouble() * (90 - 10) + 10,
                    random.nextDouble() * (90 - 10) + 10);
            estrellaRepository.save(estrella);
            if (random.nextDouble() < 1) {
                int numPlanets = random.nextInt(3) + 1;
                for (int j = 0; j < numPlanets; j++) {
                    Planeta planeta = new Planeta("Planeta_" + i + "_" + j);
                    planeta.setEstrella(estrella);
                    estrella.addPlaneta(planeta);
                    planetaRepository.save(planeta);

                    for (int k = 0; k < 3; k++) {
                        InventarioPlaneta inventarioPlaneta = new InventarioPlaneta();
                        inventarioPlaneta.setPlaneta(planeta);
                        inventarioPlaneta.setCantidad(20.2 + k * 2);
                        inventarioPlaneta.setfOfertaDemanda(random.nextDouble() * 1000000);
                        inventarioPlaneta.setProducto(productos.get(k));
                        inventarioPlanetaRepository.save(inventarioPlaneta);
                    }
                }
            }
        }

        for (Nave nave : naves) {
            Partida partida = new Partida(0.0, nave.getDinero(), 1.0);
            partida.setNave(nave);
            nave.setPartida(partida);
            partidaRepository.save(partida);
            naveRepository.save(nave);
        }
    }
}