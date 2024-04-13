package com.example.PrimeraEntregaWeb.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.PrimeraEntregaWeb.model.Estrella;
import com.example.PrimeraEntregaWeb.model.Planeta;

@Repository
public interface EstrellaRepository extends JpaRepository<Estrella, Long> {

    @Query("SELECT DISTINCT p FROM Estrella e JOIN e.planetas p WHERE SIZE(e.planetas) > 0")
    List<Planeta> findPlanetasinEstrellas();

    @Query("SELECT e FROM Estrella e ORDER BY SQRT(POWER(e.x - :x, 2) + POWER(e.y - :y, 2) + POWER(e.z - :z, 2))")
    List<Estrella> findNearestStars(@Param("x") double x, @Param("y") double y, @Param("z") double z,
            Pageable pageable);

}
