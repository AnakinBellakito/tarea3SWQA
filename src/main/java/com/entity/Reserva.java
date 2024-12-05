package com.entity;
import java.time.LocalDateTime;

public class Reserva {
    private String id;
    private Sala sala;
    private Usuario usuario;
    private LocalDateTime fecha;
    private String detalle;

    public Reserva(String id, Sala sala, Usuario usuario, LocalDateTime fecha, String detalle) {
        this.id = id;
        this.sala = sala;
        this.usuario = usuario;
        this.fecha = fecha;
        this.detalle = detalle;
    }

    // Getters y setters
    public String getId() { return id; }
    public Sala getSala() { return sala; }
    public Usuario getUsuario() { return usuario; }
    public LocalDateTime getFecha() { return fecha; }
    public String getDetalle() { return detalle; }

    public void setDetalle(String detalle) { this.detalle = detalle; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}