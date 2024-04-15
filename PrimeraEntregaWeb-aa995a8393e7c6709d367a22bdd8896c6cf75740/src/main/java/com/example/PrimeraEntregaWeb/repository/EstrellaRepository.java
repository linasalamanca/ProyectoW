package com.example.PrimeraEntregaWeb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.Planeta;

@Repository
public interface EstrellaRepository extends JpaRepository<Estrella, Long> {

    @Query("SELECT p FROM Estrella e JOIN e.planetas p WHERE e.id = :id")
    List<Planeta> findPlanetasinEstrellas(@Param("id") Long id);

    @Query(value = "SELECT *, SQRT(POWER(coordenadaX - :x, 2) + POWER(coordenadaY - :y, 2) + POWER(coordenadaZ - :z, 2)) AS distancia FROM estrella ORDER BY distancia LIMIT 10", nativeQuery = true)
    List<Estrella> findNearestStars(@Param("x") double x, @Param("y") double y, @Param("z") double z
    /* Pageable pageable */);

}
