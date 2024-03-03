package com.example.PrimeraEntregaWeb.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private String nombre;

    @Column(name = "Jugadores", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private List<Jugador> jugadores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Equipo(Long id, @NotBlank(message = "no puede estar en blanco") String nombre,
            @NotBlank(message = "no puede estar en blanco") List<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.jugadores = jugadores;
    }

    public Equipo() {
    }

}
