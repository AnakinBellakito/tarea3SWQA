package com.entity;
public class Sala {
    private String codigo;
    private String nombre;
    private String ubicacion;
    private boolean reservada;

    public Sala(String codigo, String nombre, String ubicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.reservada = false;
    }

    // Getters y setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public boolean isReservada() { return reservada; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public void setReservada(boolean reservada) { this.reservada = reservada; }
}