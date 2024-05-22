package com.example.PrimeraEntregaWeb.dto;

public class CompraDTO {
    private Long idJugador;
    private Long idInventario;
    
    public CompraDTO(Long idJugador, Long idInventario) {
        this.idJugador = idJugador;
        this.idInventario = idInventario;
    }

    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
    }

    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }

}
