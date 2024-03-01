package com.example.PrimeraEntregaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrimeraEntregaWeb.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
