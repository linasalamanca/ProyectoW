package com.example.PrimeraEntregaWeb.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.PrimeraEntregaWeb.model.InventarioPlaneta;
import com.example.PrimeraEntregaWeb.model.Producto;

public interface InventarioPlanetaRepository extends JpaRepository<InventarioPlaneta, Long> {

    @Query("SELECT Producto FROM InventarioPlaneta as ip JOIN Planeta as p WHERE (ip.id = p.id) and p.id = :id")
    List<InventarioPlaneta> buscarProductos(@Param("id") Long id);

}
