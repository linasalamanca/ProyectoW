package com.example.PrimeraEntregaWeb.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class InventarioPlaneta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double cantidad;

    @Column(name = "fOfertaDemanda", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double fOfertaDemanda;

    @ManyToOne
    @JsonIgnore
    private Producto producto;

    @ManyToOne
    @JsonIgnore
    private Planeta planeta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getfOfertaDemanda() {
        return fOfertaDemanda;
    }

    public void setfOfertaDemanda(Double fOfertaDemanda) {
        this.fOfertaDemanda = fOfertaDemanda;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }

    public InventarioPlaneta(Double i, Double fOfertaDemanda) {
        this.cantidad = i;
        this.fOfertaDemanda = fOfertaDemanda;
    }

    public InventarioPlaneta() {

    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
