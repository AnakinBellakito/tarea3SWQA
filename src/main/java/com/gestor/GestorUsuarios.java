package com.gestor;
import java.util.*;

import com.entity.Usuario;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public void agregarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getIdentificador())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese identificador");
        }
        usuarios.put(usuario.getIdentificador(), usuario);
    }

    public Usuario obtenerUsuario(String identificador) {
        return usuarios.get(identificador);
    }

    public void actualizarUsuario(Usuario usuario) {
        if (!usuarios.containsKey(usuario.getIdentificador())) {
            throw new IllegalArgumentException("No existe el usuario especificado");
        }
        usuarios.put(usuario.getIdentificador(), usuario);
    }

    public void eliminarUsuario(String identificador) {
        if (!usuarios.containsKey(identificador)) {
            throw new IllegalArgumentException("No existe el usuario especificado");
        }
        usuarios.remove(identificador);
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios.values());
    }
}