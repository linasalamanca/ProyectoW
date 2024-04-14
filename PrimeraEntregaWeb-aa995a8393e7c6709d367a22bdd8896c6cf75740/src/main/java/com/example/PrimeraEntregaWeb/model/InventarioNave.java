package com.example.PrimeraEntregaWeb.model;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class InventarioNave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double cantidad;

    @Column(name = "fOfertaDemanda", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double fOfertaDemanda = 0.0;

    public Double getfOfertaDemanda() {
        return fOfertaDemanda;
    }

    public void setfOfertaDemanda(Double fOfertaDemanda) {
        this.fOfertaDemanda = fOfertaDemanda;
    }

    @ManyToOne
    private Nave nave;

    @ManyToOne
    private Producto producto;

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

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public InventarioNave(Double cantidad) {
        this.cantidad = cantidad;
    }

    public InventarioNave() {

    }

    public InventarioNave(Double cantidad, Double fOfertaDemanda) {
        this.cantidad = cantidad;
        this.fOfertaDemanda = fOfertaDemanda;
    }

}
