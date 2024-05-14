package com.example.PrimeraEntregaWeb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

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


@ActiveProfiles("integration-testing")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ComprarProductoControllerTest {
    private static final String SERVER_URL = "http://localhost:8081";

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
    void init(){

         List<TipoNave> tipoNaves = new ArrayList<>();
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                        TipoNave tipoNave = new TipoNave("tipoNave" + i, random.nextDouble() ,
                                        random.nextDouble());
                        tipoNaves.add(tipoNave);
                }

                tipoNaveRepository.saveAll(tipoNaves);

        List<Producto> productos = new ArrayList<>();
        for(int i=0; i<3; i++){
            productos.add(new Producto((double) i+1, "producto-prueba"+i));
            productos.get(i).setPrecio((i+2)*10.5);
        }
        productoRepository.saveAll(productos);

        List<Nave> naves = new ArrayList<>();
        int min = 100;
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

        Partida partida = new Partida(0.0, naves.get(0).getDinero(), 1.0);
        partidaRepository.save(partida);
    }

    @Autowired
    private TestRestTemplate rest;

    @Test
    void testComprar() {
        
    }
}
