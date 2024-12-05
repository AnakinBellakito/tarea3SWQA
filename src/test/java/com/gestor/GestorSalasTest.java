package com.gestor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.entity.*;
import com.gestor.GestorSalas;

public class GestorSalasTest {
    private GestorSalas gestorSalas;
    private Sala salaTest;

    @Before
    public void setUp() {
        gestorSalas = new GestorSalas();
        salaTest = new Sala("S001", "Sala Juntas", "Piso 1");
    }

    @Test
    public void testAgregarSala() {
        gestorSalas.agregarSala(salaTest);
        Sala salaRecuperada = gestorSalas.obtenerSala("S001");
        
        assertNotNull("La sala debería existir", salaRecuperada);
        assertEquals("Los códigos deberían coincidir", "S001", salaRecuperada.getCodigo());
        assertEquals("Los nombres deberían coincidir", "Sala Juntas", salaRecuperada.getNombre());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarSalaDuplicada() {
        gestorSalas.agregarSala(salaTest);
        gestorSalas.agregarSala(salaTest); // Debería lanzar excepción
    }

    @Test
    public void testActualizarSala() {
        gestorSalas.agregarSala(salaTest);
        salaTest.setNombre("Sala Ejecutiva");
        gestorSalas.actualizarSala(salaTest);
        
        Sala salaActualizada = gestorSalas.obtenerSala("S001");
        assertEquals("El nombre debería estar actualizado", "Sala Ejecutiva", salaActualizada.getNombre());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarSalaInexistente() {
        Sala salaInexistente = new Sala("S999", "Sala Fantasma", "Piso X");
        gestorSalas.actualizarSala(salaInexistente); // Debería lanzar excepción
    }

    @Test
    public void testEliminarSala() {
        gestorSalas.agregarSala(salaTest);
        gestorSalas.eliminarSala("S001");
        assertNull("La sala no debería existir", gestorSalas.obtenerSala("S001"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEliminarSalaInexistente() {
        gestorSalas.eliminarSala("S999"); // Debería lanzar excepción
    }

    @Test
    public void testListarSalas() {
        gestorSalas.agregarSala(salaTest);
        Sala otraSala = new Sala("S002", "Sala Reuniones", "Piso 2");
        gestorSalas.agregarSala(otraSala);
        
        assertEquals("Deberían haber 2 salas", 2, gestorSalas.listarSalas().size());
    }
}