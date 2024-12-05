package com.gestor;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.entity.Usuario;
import com.gestor.GestorUsuarios;

public class GestorUsuariosTest {
    private GestorUsuarios gestorUsuarios;
    private Usuario usuarioTest;

    @Before
    public void setUp() {
        gestorUsuarios = new GestorUsuarios();
        usuarioTest = new Usuario("U001", "Juan Pérez", "Sistemas", "Desarrollador Senior");
    }

    @Test
    public void testAgregarUsuario() {
        gestorUsuarios.agregarUsuario(usuarioTest);
        Usuario usuarioRecuperado = gestorUsuarios.obtenerUsuario("U001");
        
        assertNotNull("El usuario debería existir", usuarioRecuperado);
        assertEquals("Los identificadores deberían coincidir", "U001", usuarioRecuperado.getIdentificador());
        assertEquals("Los nombres deberían coincidir", "Juan Pérez", usuarioRecuperado.getNombre());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAgregarUsuarioDuplicado() {
        gestorUsuarios.agregarUsuario(usuarioTest);
        gestorUsuarios.agregarUsuario(usuarioTest); // Debería lanzar excepción
    }

    @Test
    public void testActualizarUsuario() {
        gestorUsuarios.agregarUsuario(usuarioTest);
        usuarioTest.setDepartamento("Desarrollo");
        gestorUsuarios.actualizarUsuario(usuarioTest);
        
        Usuario usuarioActualizado = gestorUsuarios.obtenerUsuario("U001");
        assertEquals("El departamento debería estar actualizado", "Desarrollo", 
                    usuarioActualizado.getDepartamento());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testActualizarUsuarioInexistente() {
        Usuario usuarioInexistente = new Usuario("U999", "Fantasma", "N/A", "N/A");
        gestorUsuarios.actualizarUsuario(usuarioInexistente); // Debería lanzar excepción
    }

    @Test
    public void testEliminarUsuario() {
        gestorUsuarios.agregarUsuario(usuarioTest);
        gestorUsuarios.eliminarUsuario("U001");
        assertNull("El usuario no debería existir", gestorUsuarios.obtenerUsuario("U001"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEliminarUsuarioInexistente() {
        gestorUsuarios.eliminarUsuario("U999"); // Debería lanzar excepción
    }

    @Test
    public void testListarUsuarios() {
        gestorUsuarios.agregarUsuario(usuarioTest);
        Usuario otroUsuario = new Usuario("U002", "Ana García", "RRHH", "Gerente RRHH");
        gestorUsuarios.agregarUsuario(otroUsuario);
        
        assertEquals("Deberían haber 2 usuarios", 2, gestorUsuarios.listarUsuarios().size());
    }
}