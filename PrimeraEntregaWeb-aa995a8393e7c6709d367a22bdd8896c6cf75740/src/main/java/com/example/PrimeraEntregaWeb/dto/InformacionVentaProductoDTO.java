package com.example.PrimeraEntregaWeb.dto;

public class InformacionVentaProductoDTO {
    private String nombreProducto;
    private Double cantidad;
    private Double precio;
    private Double oferta;

    public InformacionVentaProductoDTO(String nombreProducto, Double cantidad, Double oferta) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.oferta = oferta;
    }

    public InformacionVentaProductoDTO() {
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getOferta() {
        return oferta;
    }

    public void setOferta(Double oferta) {
        this.oferta = oferta;
    }

}
