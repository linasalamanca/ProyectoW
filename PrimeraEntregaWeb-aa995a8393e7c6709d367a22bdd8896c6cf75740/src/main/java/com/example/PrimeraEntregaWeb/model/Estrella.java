package com.example.PrimeraEntregaWeb.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Estrella {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coordenadaX", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double coordenadaX;

    @Column(name = "coordenadaY", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double coordenadaY;

    @Column(name = "coordenadaZ", nullable = false)
    @NotBlank(message = "no puede estar en blanco")
    private Double coordenadaZ;

    @OneToMany(mappedBy = "estrella")
    @JsonIgnore
    private List<Planeta> planetas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaZ() {
        return coordenadaZ;
    }

    public void setCoordenadaZ(Double coordenadaZ) {
        this.coordenadaZ = coordenadaZ;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public List<Planeta> getPlanetas() {
        return planetas;
    }

    public void setPlanetas(List<Planeta> planetas) {
        this.planetas = planetas;
    }

    public void addPlaneta(Planeta p) {
        this.planetas.add(p);
    }

    public Estrella(Double coordenadaX, Double coordenadaY, Double coordenadaZ) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.coordenadaZ = coordenadaZ;
    }

    public Estrella() {

    }
}
