package com.example.PrimeraEntregaWeb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PrimeraEntregaWeb.model.Estrella;

@Repository
public interface EstrellaRepository extends JpaRepository<Estrella, Long> {

}
