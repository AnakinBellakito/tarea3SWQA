package com.gestor;
import java.time.*;
import java.util.*;

import com.entity.Reserva;
import com.entity.Sala;
import com.entity.Usuario;

public class GestorReservas {
    private Map<String, Reserva> reservas;
    private GestorSalas gestorSalas;
    private GestorUsuarios gestorUsuarios;

    public GestorReservas(GestorSalas gestorSalas, GestorUsuarios gestorUsuarios) {
        this.reservas = new HashMap<>();
        this.gestorSalas = gestorSalas;
        this.gestorUsuarios = gestorUsuarios;
    }

    public void crearReserva(String id, String codigoSala, String idUsuario, LocalDateTime fecha, String detalle) {
        // Verificar si la sala y el usuario existen
        Sala sala = gestorSalas.obtenerSala(codigoSala);
        Usuario usuario = gestorUsuarios.obtenerUsuario(idUsuario);
        
        if (sala == null || usuario == null) {
            throw new IllegalArgumentException("Sala o usuario no encontrado");
        }

        // Verificar si la sala está disponible
        if (sala.isReservada()) {
            throw new IllegalArgumentException("La sala ya está reservada");
        }

        // Verificar si el usuario ya tiene una reserva en ese horario
        if (tieneReservaSimultanea(usuario, fecha)) {
            throw new IllegalArgumentException("El usuario ya tiene una reserva en ese horario");
        }

        Reserva reserva = new Reserva(id, sala, usuario, fecha, detalle);
        sala.setReservada(true);
        reservas.put(id, reserva);
    }

    private boolean tieneReservaSimultanea(Usuario usuario, LocalDateTime fecha) {
        return reservas.values().stream()
            .anyMatch(r -> r.getUsuario().getIdentificador().equals(usuario.getIdentificador()) &&
                     r.getFecha().equals(fecha));
    }

    public Reserva obtenerReserva(String id) {
        return reservas.get(id);
    }

    public void actualizarReserva(Reserva reserva) {
        if (!reservas.containsKey(reserva.getId())) {
            throw new IllegalArgumentException("No existe la reserva especificada");
        }
        reservas.put(reserva.getId(), reserva);
    }

    public void eliminarReserva(String id) {
        Reserva reserva = reservas.get(id);
        if (reserva == null) {
            throw new IllegalArgumentException("No existe la reserva especificada");
        }
        reserva.getSala().setReservada(false);
        reservas.remove(id);
    }

    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas.values());
    }
}