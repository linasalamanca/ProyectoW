package com.example.PrimeraEntregaWeb.dto;

public class InformacionVentaProductoDTO {
    private String nombreProducto;
    private Double cantidad;
    private Double precio;

    public InformacionVentaProductoDTO(String nombreProducto, Double cantidad, Double precio) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
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

}
