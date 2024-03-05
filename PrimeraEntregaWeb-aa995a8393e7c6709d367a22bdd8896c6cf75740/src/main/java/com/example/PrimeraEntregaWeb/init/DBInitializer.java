package com.example.PrimeraEntregaWeb.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.model.Producto;
import com.example.PrimeraEntregaWeb.model.TipoNave;
import com.example.PrimeraEntregaWeb.repository.EstrellaRepository;
import com.example.PrimeraEntregaWeb.repository.InventarioNaveRepository;
import com.example.PrimeraEntregaWeb.repository.InventarioPlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.JugadorRepository;
import com.example.PrimeraEntregaWeb.repository.NaveRepository;
import com.example.PrimeraEntregaWeb.repository.PlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.ProductoRepository;
import com.example.PrimeraEntregaWeb.repository.TipoNaveRepository;

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

        @Override
        public void run(String... args) throws Exception {

                Logger loggy = LoggerFactory.getLogger(getClass());
                /*
                 * Nave nave1 = new Nave(200, 12.4, 24.6, 67.3, "nave1", 20.0);
                 * Nave nave2 = new Nave(300, 18.9, 13.3, 11.9, "nave2", 50.0);
                 * Nave nave3 = new Nave(400, 17.7, 32.8, 17.1, "nave3", 40.0);
                 * Nave nave4 = new Nave(500, 11.4, 13.6, 13.2, "nave4", 64.0);
                 * Nave nave5 = new Nave(600, 43.2, 12.1, 12.8, "nave5", 38.0);
                 * 
                 * List<Nave> naves = Arrays.asList(nave1, nave2, nave3, nave4, nave5);
                 * naveRepository.saveAll(naves);
                 * 
                 * Estrella estrella1 = new Estrella(76.2, 24.6, 67.3);
                 * Estrella estrella2 = new Estrella(18.9, 13.3, 11.9);
                 * Estrella estrella3 = new Estrella(17.7, 32.8, 17.1);
                 * Estrella estrella4 = new Estrella(11.4, 13.6, 13.2);
                 * Estrella estrella5 = new Estrella(43.2, 12.1, 12.8);
                 * 
                 * List<Estrella> estrellas = Arrays.asList(estrella1, estrella2, estrella3,
                 * estrella4, estrella5);
                 * estrellaRepository.saveAll(estrellas);
                 * 
                 * Jugador jugador1 = new Jugador("a", "usuario1", "hola");
                 * Jugador jugador2 = new Jugador("b", "usuario2", "hola");
                 * Jugador jugador3 = new Jugador("c", "usuario3", "hola");
                 * Jugador jugador4 = new Jugador("d", "usuario4", "hola");
                 * Jugador jugador5 = new Jugador("e", "usuario5", "hola");
                 * 
                 * List<Jugador> jugadores = Arrays.asList(jugador1, jugador2, jugador3,
                 * jugador4, jugador5);
                 * jugadorRepository.saveAll(jugadores);
                 * 
                 * Planeta planeta1 = new Planeta("planeta1");
                 * Planeta planeta2 = new Planeta("planeta2");
                 * Planeta planeta3 = new Planeta("planeta3");
                 * Planeta planeta4 = new Planeta("planeta4");
                 * Planeta planeta5 = new Planeta("planeta5");
                 * 
                 * List<Planeta> planetas = Arrays.asList(planeta1, planeta2, planeta3,
                 * planeta4, planeta5);
                 * planetaRepository.saveAll(planetas);
                 * 
                 * Producto producto1 = new Producto(2.4, "tipo1");
                 * Producto producto2 = new Producto(65.7, "tipo2");
                 * Producto producto3 = new Producto(53.6, "tipo3");
                 * Producto producto4 = new Producto(2.74, "tipo4");
                 * Producto producto5 = new Producto(74.21, "tipo5");
                 * 
                 * List<Producto> productos = Arrays.asList(producto1, producto2, producto3,
                 * producto4, producto5);
                 * productoRepository.saveAll(productos);
                 * 
                 * TipoNave tipoNave1 = new TipoNave("tipoNave1", 200.0, 12.4);
                 * TipoNave tipoNave2 = new TipoNave("tipoNave2", 300.0, 18.9);
                 * TipoNave tipoNave3 = new TipoNave("tipoNave3", 400.0, 17.7);
                 * TipoNave tipoNave4 = new TipoNave("tipoNave4", 500.0, 11.4);
                 * TipoNave tipoNave5 = new TipoNave("tipoNave5", 600.0, 43.2);
                 * 
                 * List<TipoNave> tipoNaves = Arrays.asList(tipoNave1, tipoNave2, tipoNave3,
                 * tipoNave4, tipoNave5);
                 * tipoNaveRepository.saveAll(tipoNaves);
                 * 
                 * InventarioNave inave1 = new InventarioNave(615);
                 * InventarioNave inave2 = new InventarioNave(200);
                 * InventarioNave inave3 = new InventarioNave(300);
                 * InventarioNave inave4 = new InventarioNave(400);
                 * InventarioNave inave5 = new InventarioNave(500);
                 * 
                 * List<InventarioNave> inventarioNave = Arrays.asList(inave1, inave2, inave3,
                 * inave4, inave5);
                 * inventarioNaveRepository.saveAll(inventarioNave);
                 * 
                 * InventarioPlaneta iplaneta1 = new InventarioPlaneta(615, 9.1);
                 * InventarioPlaneta iplaneta2 = new InventarioPlaneta(200, 9.9);
                 * InventarioPlaneta iplaneta3 = new InventarioPlaneta(300, 81.2);
                 * InventarioPlaneta iplaneta4 = new InventarioPlaneta(400, 821.1);
                 * InventarioPlaneta iplaneta5 = new InventarioPlaneta(500, 12.2);
                 * 
                 * List<InventarioPlaneta> inventarioPlaneta = Arrays.asList(iplaneta1,
                 * iplaneta2, iplaneta3, iplaneta4, iplaneta5);
                 * inventarioPlanetaRepository.saveAll(inventarioPlaneta);
                 * 
                 */
                /* Crear tipos de nave */
                List<TipoNave> tipoNaves = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < 20; i++) {
                        TipoNave tipoNave = new TipoNave("tipoNave" + i, random.nextDouble() * 2.5,
                                        random.nextDouble() * 3.6);
                        tipoNaves.add(tipoNave);
                }

                tipoNaveRepository.saveAll(tipoNaves);

                /* Crear lista de naves */
                List<Nave> naves = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                        Nave nave = new Nave(
                                        random.nextInt(900) + 100,
                                        random.nextDouble() * 400 + 50,
                                        random.nextDouble() * 400 + 50,
                                        random.nextDouble() * 400 + 50,
                                        "nave" + i,
                                        random.nextDouble() * 200 + 15);
                        nave.setTipo(tipoNaves.get(i));
                        naves.add(nave);
                }

                /* Crear lista de jugadores */
                List<Jugador> jugadores = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                        Jugador jugador = new Jugador("rol" + i, "jugador" + i, "hola" + i);
                        jugadores.add(jugador);
                }

                /* Dividir los jugadores en los equipos */
                List<List<Jugador>> equipos = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                        List<Jugador> equipo = new ArrayList<>();
                        for (int j = 0; j < 10; j++) {
                                equipo.add(jugadores.get(i * 10 + j));
                        }
                        equipos.add(equipo);
                }

                /* Asignar a cada nave un equipo */
                for (int i = 0; i < 10; i++) {
                        List<Jugador> equipo = equipos.get(i);
                        Nave nave = naves.get(i);
                        for (Jugador jugador : equipo) {
                                jugador.setNave(nave);
                                nave.addJugador(jugador);
                        }
                }

                naveRepository.saveAll(naves);
                jugadorRepository.saveAll(jugadores);

                /* Generar las estrellas */
                for (int i = 0; i < 400; i++) {
                        Estrella estrella = new Estrella(
                                        random.nextDouble() * 100,
                                        random.nextDouble() * 100,
                                        random.nextDouble() * 100);

                        estrellaRepository.save(estrella);

                        if (random.nextDouble() < 0.01) {
                                int numPlanets = random.nextInt(3) + 1;
                                loggy.info("numPlanteas" + numPlanets);
                                for (int j = 0; j < numPlanets; j++) {
                                        Planeta planeta = new Planeta("Planeta_" + i + "_" + j);
                                        planeta.setEstrella(estrella);
                                        estrella.addPlaneta(planeta);
                                        planetaRepository.save(planeta);
                                        // loggy.info("estrellaaaa " + estrella);
                                }
                        }
                }

                List<Producto> productos = new ArrayList<>();
                /* Generar productos */
                for (int i = 0; i < 500; i++) {
                        Producto producto = new Producto((i * 3.6), "tipo" + (i + 10));
                        productos.add(producto);
                }
                productoRepository.saveAll(productos);
                /*
                 * List<TipoNave> tipoNaves = new ArrayList<>();
                 * /* Generar tipos de nave
                 * for (int i = 0; i < 20; i++) {
                 * TipoNave tn = new TipoNave("Tipo" + i, 2.5 * i, 3.6 * i);
                 * tipoNaves.add(tn);
                 * }
                 * tipoNaveRepository.saveAll(tipoNaves);
                 */

                /* Invemtairo */
                InventarioNave inave1 = new InventarioNave(615.7);
                InventarioNave inave2 = new InventarioNave(200.4);
                InventarioNave inave3 = new InventarioNave(300.6);
                InventarioNave inave4 = new InventarioNave(400.4);
                InventarioNave inave5 = new InventarioNave(500.6);

                List<InventarioNave> inventarioNave = Arrays.asList(inave1, inave2, inave3,
                                inave4, inave5);
                inventarioNaveRepository.saveAll(inventarioNave);

                InventarioPlaneta iplaneta1 = new InventarioPlaneta(615, 9.1);
                InventarioPlaneta iplaneta2 = new InventarioPlaneta(200, 9.9);
                InventarioPlaneta iplaneta3 = new InventarioPlaneta(300, 81.2);
                InventarioPlaneta iplaneta4 = new InventarioPlaneta(400, 821.1);
                InventarioPlaneta iplaneta5 = new InventarioPlaneta(500, 12.2);

                List<InventarioPlaneta> inventarioPlaneta = Arrays.asList(iplaneta1,
                                iplaneta2, iplaneta3, iplaneta4, iplaneta5);
                inventarioPlanetaRepository.saveAll(inventarioPlaneta);
        }

}
