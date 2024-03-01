package com.example.PrimeraEntregaWeb.model;

public class InventarioPlaneta {
    private Integer cantidad;
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

}
