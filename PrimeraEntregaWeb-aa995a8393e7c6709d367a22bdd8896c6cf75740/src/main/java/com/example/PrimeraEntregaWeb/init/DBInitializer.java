package com.example.PrimeraEntregaWeb.init;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

                Logger loggy = LoggerFactory.getLogger(getClass());

                List<Producto> productos = new ArrayList<>();
                /* Generar productos */
                for (int i = 0; i < 10; i++) {
                        Producto producto = new Producto((i * 3.6), "tipo" + (i + 10));
                        productos.add(producto);

                }
                productoRepository.saveAll(productos);

                /* Crear tipos de nave */
                List<TipoNave> tipoNaves = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                        TipoNave tipoNave = new TipoNave("tipoNave" + i, random.nextDouble() * 2.5,
                                        random.nextDouble() * 3.6);
                        tipoNaves.add(tipoNave);
                }

                tipoNaveRepository.saveAll(tipoNaves);

                /* Crear lista de naves */
                List<Nave> naves = new ArrayList<>();
                int min = 1000000;
                int cont = 0;
                for (int i = 0; i < 5; i++) {
                        Nave nave = new Nave(
                                        random.nextDouble() + min,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        "nave" + i,
                                        random.nextDouble() * 200 + 15, random.nextDouble() * 500.5);
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

                /* Crear lista de jugadores */
                List<Jugador> jugadores = new ArrayList<>();
                for (int i = 0; i < 25; i++) {
                        Jugador jugador = new Jugador("rol" + i, "jugador" + i, "hola" + i);
                        jugadores.add(jugador);
                }

                /* Dividir los jugadores en los equipos */
                List<List<Jugador>> equipos = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                        List<Jugador> equipo = new ArrayList<>();
                        for (int j = 0; j < 5; j++) {
                                equipo.add(jugadores.get(i * 5 + j));
                        }
                        equipos.add(equipo);
                }

                /* Asignar a cada nave un equipo */
                for (int i = 0; i < 5; i++) {
                        List<Jugador> equipo = equipos.get(i);
                        Nave nave = naves.get(i);
                        for (Jugador jugador : equipo) {
                                jugador.setNave(nave);
                                nave.addJugador(jugador);
                        }
                }

                jugadorRepository.saveAll(jugadores);

                int cont2 = 0;
                /* Generar las estrellas */
                for (int i = 0; i < 100; i++) {
                        Estrella estrella = new Estrella(
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10,
                                        random.nextDouble() * (90 - 10) + 10);

                        estrellaRepository.save(estrella);
                        if (random.nextDouble() < 1) {
                                int numPlanets = random.nextInt(3) + 1;
                                loggy.info("numPlanetas" + numPlanets);
                                for (int j = 0; j < numPlanets; j++) {
                                        Planeta planeta = new Planeta("Planeta_" + i + "_" + j);
                                        planeta.setEstrella(estrella);
                                        estrella.addPlaneta(planeta);
                                        planetaRepository.save(planeta);

                                        for (int k = 0; k < 3; k++) {
                                                InventarioPlaneta inventarioPlaneta = new InventarioPlaneta();
                                                inventarioPlaneta.setPlaneta(planeta);
                                                inventarioPlaneta.setCantidad(20.2 + k * 2);

                                                Double randomNumber = random.nextDouble(1000001);
                                                inventarioPlaneta.setfOfertaDemanda(randomNumber);

                                                if (cont2 == 10) {
                                                        cont2 = 0;
                                                }
                                                if (cont2 < 10) {
                                                        inventarioPlaneta.setProducto(productos.get(cont2));
                                                        cont2++;
                                                }
                                                inventarioPlanetaRepository.save(inventarioPlaneta);
                                        }

                                }

                        }
                }

                /* Inventario */
                /*
                 * InventarioNave inave1 = new InventarioNave(615.7);
                 * InventarioNave inave2 = new InventarioNave(200.4);
                 * InventarioNave inave3 = new InventarioNave(300.6);
                 * InventarioNave inave4 = new InventarioNave(400.4);
                 * InventarioNave inave5 = new InventarioNave(500.6);
                 * 
                 * List<InventarioNave> inventarioNave = Arrays.asList(inave1, inave2, inave3,
                 * inave4, inave5);
                 * inventarioNaveRepository.saveAll(inventarioNave);
                 */

                Partida partida = new Partida(0.0, naves.get(0).getDinero(), 1.0);
                partidaRepository.save(partida);

                /*
                 * InventarioPlaneta iplaneta1 = new InventarioPlaneta(615.2, 9.1);
                 * InventarioPlaneta iplaneta2 = new InventarioPlaneta(200.0, 9.9);
                 * InventarioPlaneta iplaneta3 = new InventarioPlaneta(300.5, 81.2);
                 * InventarioPlaneta iplaneta4 = new InventarioPlaneta(400.6, 821.1);
                 * InventarioPlaneta iplaneta5 = new InventarioPlaneta(500.5, 12.2);
                 * 
                 * List<InventarioPlaneta> inventarioPlaneta = Arrays.asList(iplaneta1,
                 * iplaneta2, iplaneta3, iplaneta4, iplaneta5);
                 * inventarioPlanetaRepository.saveAll(inventarioPlaneta);
                 */

                /*
                 * Random rand = new Random();
                 * int i = 0;
                 * for (Planeta planeta : planetaRepository.findAll()) {
                 * i++;
                 * int productoAleatorio = rand.nextInt(10);
                 * InventarioPlaneta inventarioPlaneta = new InventarioPlaneta();
                 * inventarioPlaneta.setPlaneta(planeta);
                 * inventarioPlaneta.setCantidad(20.2 + i * 2);
                 * inventarioPlaneta.setProducto(productos.get(productoAleatorio));
                 * inventarioPlaneta.setfOfertaDemanda(0.2 + i / 100);
                 * 
                 * inventarioPlanetaRepository.save(inventarioPlaneta);
                 */
        }
}
