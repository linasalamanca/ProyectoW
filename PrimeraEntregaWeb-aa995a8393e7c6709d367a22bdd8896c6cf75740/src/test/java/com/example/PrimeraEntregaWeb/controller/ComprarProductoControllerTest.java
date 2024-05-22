package com.example.PrimeraEntregaWeb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.h2.mvstore.tx.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.http.HttpStatus;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.InventarioNave;
import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.PrimeraEntregaWeb.controller.ComprarProductoController;
import com.example.PrimeraEntregaWeb.dto.InformacionCompraProductoDTO;
import com.example.PrimeraEntregaWeb.dto.InformacionVentaProductoDTO;
import com.example.PrimeraEntregaWeb.model.Partida;

@ActiveProfiles("integration-testing")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ComprarProductoControllerTest {
        private static final String SERVER_URL = "http://localhost:8081";
        private InformacionCompraProductoDTO informacion = new InformacionCompraProductoDTO();
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

        @BeforeEach
        void init() {

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
                int min = 100;
                int cont = 0;
                for (int i = 0; i < 5; i++) {
                        Nave nave = new Nave(
                                        1000.52,
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
                                inventarioNave.setCantidad(20.2);
                                inventarioNave.setfOfertaDemanda(random.nextDouble() * 1000000);
                                inventarioNave.setProducto(productos.get(k));
                                inventarioNaveRepository.save(inventarioNave);
                                informacion = new InformacionCompraProductoDTO(
                                                (long) 1,
                                                productos.get(k).getTipo(),
                                                inventarioNave.getCantidad(),
                                                inventarioNave.getfOfertaDemanda(),
                                                (inventarioNave.getfOfertaDemanda()
                                                                / (1 + inventarioNave.getCantidad())));
                        }
                }

                int cont2 = 0;
                /* Generar las estrellas */
                for (int i = 0; i < 5; i++) {
                        Estrella estrella = new Estrella(
                                        random.nextDouble() * (90 - 10) + 10,
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

                                                Double randomNumber = random.nextDouble(1000001);
                                                inventarioPlaneta.setfOfertaDemanda(randomNumber);

                                                if (cont2 == 3) {
                                                        cont2 = 0;
                                                }
                                                if (cont2 < 3) {
                                                        inventarioPlaneta.setProducto(productos.get(cont2));
                                                        cont2++;
                                                }
                                                inventarioPlanetaRepository.save(inventarioPlaneta);
                                        }

                                }

                        }
                }

                Partida partida = new Partida(0.0, naves.get(0).getDinero(), 1.0);
                partidaRepository.save(partida);
        }

        @Autowired
        private TestRestTemplate rest;

        // prueba de get, comando para correr mvn test
        // -Dtest=ComprarProductoControllerTest#traerPuntaje
        @Test
        void traerPuntaje() {
                Double puntaje = rest.getForObject(SERVER_URL + "/api/comprar/obtener-puntaje", Double.class);
                assertEquals(1000.52, puntaje);
        }

        // prueba de get para traer el tiempo, comando para correr: mvn test
        // -Dtest=ComprarProductoControllerTest#traerTiempo
        @Test
        void traerTiempo() {
                Double tiempo = rest.getForObject(SERVER_URL + "/api/escoger-estrella/tiempo", Double.class);
                assertEquals(0.0, tiempo);
        }

        // prueba de get, comando para correr mvn test
        // -Dtest=ComprarProductoControllerTest#infoVentaProducto
        @Test
        void infoVentaProducto() {
                @SuppressWarnings("unchecked")
                List<InformacionVentaProductoDTO> informacion = rest.getForObject(SERVER_URL + "/api/comprar/list/1",
                                List.class);
                assertEquals(3, informacion.size());
        }

        // prueba de patch, para acceder usar comando .\mvnw test
        // -Dtest=ComprarProductoControllerTest#puntajeActualizado

        @Test
        void puntajeActualizado() {
                RequestEntity<Void> request = RequestEntity.patch(SERVER_URL + "/api/comprar/actualizar-puntaje/1")
                                .build();
                ResponseEntity<Double> response = rest.exchange(request, Double.class);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                Double puntaje = rest.getForObject(SERVER_URL + "/api/comprar/obtener-puntaje", Double.class);
                assertEquals(950.52, puntaje);
        }

        // prueba de post, para acceder usar comando .\mvnw test
        // -Dtest=ComprarProductoControllerTest#realizarCompra
        @Test
        void realizarCompra() {
                Long productoId = 1L;
                Optional<InventarioPlaneta> inventarioPlanetaOpt = inventarioPlanetaRepository.findById(productoId);
                assertTrue(inventarioPlanetaOpt.isPresent());
                double precioProducto = inventarioPlanetaOpt.get().getProducto().getPrecio();

                System.out.println("Precio del producto: " + precioProducto);

                Partida partidaInicial = partidaRepository.findById(1L).orElseThrow();
                double puntajeInicial = partidaInicial.getPuntaje();

                RequestEntity<Void> request = RequestEntity.post(SERVER_URL + "/api/comprar/realizar-compra/1")
                                .build();
                ResponseEntity<String> response = rest.exchange(request, String.class);
                assertEquals(HttpStatus.OK, response.getStatusCode());

                Partida partidaFinal = partidaRepository.findById(1L).orElseThrow();
                double puntajeFinal = partidaFinal.getPuntaje();

                assertEquals(puntajeInicial - precioProducto, puntajeFinal);
        }

}