package com.example.PrimeraEntregaWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nave")
public class Nave {
    @Id
    private String nombre;

    @Column(name = "dinero", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double dinero;

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


    @Column(name = "capacidadMax", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double capacidadMax;

    @Column(name = "capacidadUsada", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double capacidadUsada;

    @OneToMany(mappedBy = "nave")
    private List<Jugador> jugadores = new ArrayList<>();

    @OneToMany(mappedBy = "nave")
    private List<InventarioNave> inventario = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private TipoNave tipo;

    @ManyToOne
    @JsonIgnore
    private Planeta planeta;

    @OneToOne
    @JsonIgnore
    private Partida partida;

    
    public Nave() {
    }

    public Nave(Double dinero, Double coordenadaX, Double coordenadaY, Double coordenadaZ, String nombre,
            Double velocidad, Double capacidadMax) {
        this.dinero = dinero;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.coordenadaZ = coordenadaZ;
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.capacidadMax = capacidadMax;
        this.capacidadUsada = 0.0;
    }

    public Double getDinero() {
        return dinero;
    }

    public void setDinero(Double dinero) {
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

    public void addInventario(InventarioNave iNave) {
        this.inventario.add(iNave);
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

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    public Double getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(Double capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public Double getCapacidadUsada() {
        return this.calcularCapacidadUsada();
    }

    public void setCapacidadUsada(Double capacidadUsada) {
        this.capacidadUsada = capacidadUsada;
    }
    public Double calcularCapacidadUsada(){
        for (InventarioNave iNave : this.inventario) {
            this.capacidadUsada += iNave.getCantidad() * iNave.getProducto().getVolumen();
        }
        return this.capacidadUsada;
    }
        
}
