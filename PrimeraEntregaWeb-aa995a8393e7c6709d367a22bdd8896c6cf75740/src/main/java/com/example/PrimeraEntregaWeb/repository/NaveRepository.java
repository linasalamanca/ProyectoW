package com.example.PrimeraEntregaWeb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PrimeraEntregaWeb.model.Jugador;
import com.example.PrimeraEntregaWeb.model.Nave;

@Repository
public interface NaveRepository extends JpaRepository<Nave, String> {

    @Query("SELECT DISTINCT p FROM Nave e JOIN e.jugadores p WHERE SIZE(e.jugadores) > 0")
    List<Jugador> findEquipo();
}