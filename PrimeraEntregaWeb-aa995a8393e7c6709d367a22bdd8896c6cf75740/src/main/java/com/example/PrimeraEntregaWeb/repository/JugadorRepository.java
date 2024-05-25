package com.example.PrimeraEntregaWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PrimeraEntregaWeb.model.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
   Optional<Jugador> findByUsuario(String usuario);
}
