package com.example.PrimeraEntregaWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "nave")
public class Nave {
    @Id
    private String nombre;

    @Column(name = "dinero", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private int dinero;

    @Column(name = "coordenadaX", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double coordenadaX;

    @Column(name = "coordenadaY", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double coordenadaY;

    @Column(name = "coordenadaZ", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double coordenadaZ;

    @Column(name = "velocidad", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double velocidad;

    @OneToMany(mappedBy = "nave")
    private List<Jugador> jugadores = new ArrayList<>();

    @OneToMany(mappedBy = "nave")
    private List<InventarioNave> inventario = new ArrayList<>();

    @ManyToOne
    private TipoNave tipo;

    @ManyToOne
    private Planeta planeta;

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Double getCoordenadaZ() {
        return coordenadaZ;
    }

    public void setCoordenadaZ(Double coordenadaZ) {
        this.coordenadaZ = coordenadaZ;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void addJugador(Jugador j) {
        this.jugadores.add(j);
    }

    public TipoNave getTipo() {
        return tipo;
    }

    public void setTipo(TipoNave tipo) {
        this.tipo = tipo;
    }

    public Planeta getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planeta planeta) {
        this.planeta = planeta;
    }

    public List<InventarioNave> getInventario() {
        return inventario;
    }

    public void setInventario(List<InventarioNave> inventario) {
        this.inventario = inventario;
    }

    public Nave() {
    }

    public Nave(int dinero, Double coordenadaX, Double coordenadaY, Double coordenadaZ, String nombre, Double velocidad) {
        this.dinero = dinero;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.coordenadaZ = coordenadaZ;
        this.nombre = nombre;
        this.velocidad = velocidad;
    }
}
