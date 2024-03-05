package com.example.PrimeraEntregaWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class TipoNave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "No puede estar en blanco")
    private String nombre;

    @Column(name = "volumenCarga", nullable = false)
    @NotBlank(message = "No puede estar en blanco")
    private Double volumenCarga;

    @Column(name = "velocidad", nullable = false)
    @NotBlank(message = "No puede estar en blanco")
    private Double velocidad;

    @OneToMany(mappedBy = "tipo")
    private List<Nave> naves = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVolumenCarga() {
        return volumenCarga;
    }

    public void setVolumenCarga(Double volumenCarga) {
        this.volumenCarga = volumenCarga;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Nave> getNaves() {
        return naves;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }

    public void addNave(Nave n) {
        naves.add(n);
    }

    public TipoNave(String nombre, Double volumenCarga, Double velocidad) {
        this.nombre = nombre;
        this.volumenCarga = volumenCarga;
        this.velocidad = velocidad;
    }

    public TipoNave() {

    }

}
