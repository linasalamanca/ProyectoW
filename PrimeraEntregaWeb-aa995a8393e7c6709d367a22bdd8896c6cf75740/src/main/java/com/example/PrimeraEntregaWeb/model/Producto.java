package com.example.PrimeraEntregaWeb.model;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "volumen", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double volumen;

    @Column(name = "tipo", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private String tipo;

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Producto(Double volumen, String tipo) {
        this.volumen = volumen;
        this.tipo = tipo;
    }

    public Producto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
