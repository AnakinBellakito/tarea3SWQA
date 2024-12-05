package com.gestor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.entity.Reserva;
import com.entity.Sala;
import com.entity.Usuario;
import java.time.LocalDateTime;
import com.gestor.GestorReservas;

public class GestorReservasTest {
    private GestorReservas gestorReservas;
    private GestorSalas gestorSalas;
    private GestorUsuarios gestorUsuarios;
    private Sala salaTest;
    private Usuario usuarioTest;
    private LocalDateTime fechaTest;

    @Before
    public void setUp() {
        gestorSalas = new GestorSalas();
        gestorUsuarios = new GestorUsuarios();
        gestorReservas = new GestorReservas(gestorSalas, gestorUsuarios);
        
        // Preparar datos de prueba
        salaTest = new Sala("S001", "Sala Juntas", "Piso 1");
        usuarioTest = new Usuario("U001", "Juan Pérez", "Sistemas", "Desarrollador");
        fechaTest = LocalDateTime.now().plusDays(1);
        
        // Agregar sala y usuario al sistema
        gestorSalas.agregarSala(salaTest);
        gestorUsuarios.agregarUsuario(usuarioTest);
    }

    @Test
    public void testCrearReserva() {
        gestorReservas.crearReserva("R001", "S001", "U001", fechaTest, "Reunión de proyecto");
        
        Reserva reservaCreada = gestorReservas.obtenerReserva("R001");
        assertNotNull("La reserva debería existir", reservaCreada);
        assertTrue("La sala debería estar marcada como reservada", 
                  reservaCreada.getSala().isReservada());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearReservaConSalaInexistente() {
        gestorReservas.crearReserva("R001", "S999", "U001", fechaTest, "Reunión");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearReservaConUsuarioInexistente() {
        gestorReservas.crearReserva("R001", "S001", "U999", fechaTest, "Reunión");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCrearReservaSimultanea() {
        gestorReservas.crearReserva("R001", "S001", "U001", fechaTest, "Primera reunión");
        gestorReservas.crearReserva("R002", "S001", "U001", fechaTest, "Segunda reunión");
    }

    @Test
    public void testActualizarReserva() {
        gestorReservas.crearReserva("R001", "S001", "U001", fechaTest, "Reunión inicial");
        
        Reserva reserva = gestorReservas.obtenerReserva("R001");
        LocalDateTime nuevaFecha = fechaTest.plusHours(1);
        reserva.setFecha(nuevaFecha);
        gestorReservas.actualizarReserva(reserva);
        
        Reserva reservaActualizada = gestorReservas.obtenerReserva("R001");
        assertEquals("La fecha debería estar actualizada", nuevaFecha, 
                    reservaActualizada.getFecha());
    }

    @Test
    public void testEliminarReserva() {
        gestorReservas.crearReserva("R001", "S001", "U001", fechaTest, "Reunión");
        gestorReservas.eliminarReserva("R001");
        
        assertNull("La reserva no debería existir", gestorReservas.obtenerReserva("R001"));
        assertFalse("La sala debería estar disponible", salaTest.isReservada());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEliminarReservaInexistente() {
        gestorReservas.eliminarReserva("R999");
    }

    @Test
    public void testListarReservas() {
        gestorReservas.crearReserva("R001", "S001", "U001", fechaTest, "Primera reunión");
        LocalDateTime otraFecha = fechaTest.plusDays(1);
        
        // Agregar otra sala y crear otra reserva
        Sala otraSala = new Sala("S002", "Sala 2", "Piso 2");
        gestorSalas.agregarSala(otraSala);
        gestorReservas.crearReserva("R002", "S002", "U001", otraFecha, "Segunda reunión");
        
        assertEquals("Deberían haber 2 reservas", 2, gestorReservas.listarReservas().size());
    }
}