package com.example.PrimeraEntregaWeb.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tiempo", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double tiempo;

    @Column(name = "puntaje", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double puntaje;

    @Column(name = "tiempoMax", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double tiempoMax;

    @OneToOne
    @JsonIgnore
    private Nave nave;

    public Partida( Double tiempo, Double puntaje, Double tiempoMax) {
        this.tiempo = tiempo;
        this.puntaje = puntaje;
        this.tiempoMax = tiempoMax;
    }

    public Partida() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTiempo() {
        return tiempo;
    }

    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

    public Double getTiempoMax() {
        return tiempoMax;
    }

    public void setTiempoMax(Double tiempoMax) {
        this.tiempoMax = tiempoMax;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

}
