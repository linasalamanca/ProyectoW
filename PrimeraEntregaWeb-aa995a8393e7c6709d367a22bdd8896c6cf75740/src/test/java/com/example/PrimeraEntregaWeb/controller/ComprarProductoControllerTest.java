package com.example.PrimeraEntregaWeb.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Planeta;
import com.example.PrimeraEntregaWeb.model.Producto;

import com.example.PrimeraEntregaWeb.repository.InventarioPlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.NaveRepository;
import com.example.PrimeraEntregaWeb.repository.PartidaRepository;
import com.example.PrimeraEntregaWeb.repository.PlanetaRepository;
import com.example.PrimeraEntregaWeb.repository.ProductoRepository;


@ActiveProfiles("integration-testing")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ComprarProductoControllerTest {
    private static final String SERVER_URL = "http://localhost:8081";

    @Autowired
    InventarioPlanetaRepository inventarioPlanetaRepository;

    @Autowired
    PartidaRepository partidaRepository;

    @Autowired
    NaveRepository naveRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    PlanetaRepository planetaRepository;



    @BeforeEach
    void init(){

        List<Producto> productos = new ArrayList<>();
        for(int i=0; i<3; i++){
            productos.add(new Producto((double) i+1, "producto-prueba"+i));
            productos.get(i).setPrecio((i+2)*10.5);
        }
        productoRepository.saveAll(productos);

        List<Planeta> planetas = new ArrayList<>();
        for(int i=0; i<3; i++){
            planetas.add(new Planeta("planeta-prueba"+i));
        }
        planetaRepository.saveAll(planetas);

        for(int i=0; i<6; i++){
            InventarioPlaneta inventarioPlaneta = new InventarioPlaneta((i+1)*2.4, (i+1)*4.7);
            inventarioPlaneta.setProducto(productos.get(i));
            inventarioPlaneta.setPlaneta(planetas.get(i%3));
            inventarioPlanetaRepository.save(inventarioPlaneta);
        }
    
    }
}
