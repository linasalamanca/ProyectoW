package com.example.PrimeraEntregaWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private String nombre;

    @OneToMany(mappedBy = "planeta")
    @JsonIgnore
    private List<Nave> naves = new ArrayList<>();

    @OneToMany(mappedBy = "planeta")
    @JsonIgnore
    private List<InventarioPlaneta> inventario = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "estrella_id")
    @JsonIgnore
    private Estrella estrella;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<InventarioPlaneta> getInventario() {
        return inventario;
    }

    public void setInventario(List<InventarioPlaneta> inventario) {
        this.inventario = inventario;
    }

    public Estrella getEstrella() {
        return estrella;
    }

    public void setEstrella(Estrella estrella) {
        this.estrella = estrella;
    }

    public Planeta(String nombre) {
        this.nombre = nombre;
    }

    public Planeta() {

    }

}