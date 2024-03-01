package com.example.PrimeraEntregaWeb.model;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InventarioPlaneta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Integer cantidad;

    @Column(name = "fOfertaDemanda", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Float fOfertaDemanda;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getfOfertaDemanda() {
        return fOfertaDemanda;
    }

    public void setfOfertaDemanda(Float fOfertaDemanda) {
        this.fOfertaDemanda = fOfertaDemanda;
    }

    public InventarioPlaneta(Integer cantidad, Float fOfertaDemanda) {
        this.cantidad = cantidad;
        this.fOfertaDemanda = fOfertaDemanda;
    }

    public InventarioPlaneta() {

    }

}
