package com.example.PrimeraEntregaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PrimeraEntregaWeb.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query(value = "SELECT tiempo FROM partida WHERE id = :id", nativeQuery = true)
    public Double findTiempoPartida(@Param("id") Long id);

    @Query(value = "SELECT puntaje FROM partida WHERE id = :id", nativeQuery = true)
    public Double findPuntajePartida(@Param("id") Long id);

    

}
