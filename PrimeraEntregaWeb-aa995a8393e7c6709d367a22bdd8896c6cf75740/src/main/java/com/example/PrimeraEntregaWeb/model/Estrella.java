package com.example.PrimeraEntregaWeb.model;

public class Estrella {

    private Float coordenadaX;
    private Float coordenadaY;

    public Float getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Float coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Float getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Float coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Estrella(Float coordenadaX, Float coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
}
