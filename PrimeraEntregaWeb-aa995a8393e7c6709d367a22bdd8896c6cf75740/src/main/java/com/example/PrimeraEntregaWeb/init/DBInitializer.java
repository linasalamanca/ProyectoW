package com.example.PrimeraEntregaWeb.init;

import java.util.Arrays;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.PrimeraEntregaWeb.model.Nave;
import com.example.PrimeraEntregaWeb.repository.NaveRepository;

@Component
public class DBInitializer implements CommandLineRunner {

    @Autowired
    private NaveRepository naveRepository;

    @Override
    public void run(String... args) throws Exception {

        Nave nave1 = new Nave(200, 12.4, 24.6, 67.3, "nave1", 20.0);
        Nave nave2 = new Nave(300, 18.9, 13.3, 11.9, "nave2", 50.0);
        Nave nave3 = new Nave(400, 17.7, 32.8, 17.1, "nave3", 40.0);
        Nave nave4 = new Nave(500, 11.4, 13.6, 13.2, "nave4", 64.0);
        Nave nave5 = new Nave(600, 43.2, 12.1, 12.8, "nave5", 38.0);

        List<Nave> naves = Arrays.asList(nave1, nave2, nave3, nave4, nave5);
        naveRepository.saveAll(naves);

    }

}
