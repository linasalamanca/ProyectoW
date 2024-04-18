package com.example.PrimeraEntregaWeb.dto;

public class InformacionVentaProductoDTO {
    private String nombreProducto;
    private Double cantidad;
    private Double precio;
    private Double oferta;
    private Long idInventario;

    public InformacionVentaProductoDTO(String nombreProducto, Double cantidad, Double oferta, Double precio,
            Long idInventario) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.oferta = oferta;
        this.idInventario = idInventario;
        this.precio = precio;
    }

    public InformacionVentaProductoDTO() {
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
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
