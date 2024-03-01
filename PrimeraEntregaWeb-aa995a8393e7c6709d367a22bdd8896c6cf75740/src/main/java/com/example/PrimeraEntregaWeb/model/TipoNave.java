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
    private Float volumenCarga;

    @Column(name = "velocidad", nullable = false)
    @NotBlank(message = "No puede estar en blanco")
    private Float velocidad;

    /*
     * @OneToMany(mappedBy = "naves")
     * private List<Nave> naves = new ArrayList<>();
     */

    public Float getVolumenCarga() {
        return volumenCarga;
    }

    public void setVolumenCarga(Float volumenCarga) {
        this.volumenCarga = volumenCarga;
    }

    public Float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Float velocidad) {
        this.velocidad = velocidad;
    }

    public TipoNave(String nombre, Float volumenCarga, Float velocidad) {
        this.nombre = nombre;
        this.volumenCarga = volumenCarga;
        this.velocidad = velocidad;
    }

    public TipoNave() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
