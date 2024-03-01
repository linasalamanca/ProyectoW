package com.example.PrimeraEntregaWeb.model;

public class Producto {
    private Float volumen;
    private String tipo;

    public Float getVolumen() {
        return volumen;
    }

    public void setVolumen(Float volumen) {
        this.volumen = volumen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Producto(Float volumen, String tipo) {
        this.volumen = volumen;
        this.tipo = tipo;
    }
}
