package com.gestor;
import java.util.*;

import com.entity.Sala;

public class GestorSalas {
    private Map<String, Sala> salas;

    public GestorSalas() {
        this.salas = new HashMap<>();
    }

    public void agregarSala(Sala sala) {
        if (salas.containsKey(sala.getCodigo())) {
            throw new IllegalArgumentException("Ya existe una sala con ese código");
        }
        salas.put(sala.getCodigo(), sala);
    }

    public Sala obtenerSala(String codigo) {
        return salas.get(codigo);
    }

    public void actualizarSala(Sala sala) {
        if (!salas.containsKey(sala.getCodigo())) {
            throw new IllegalArgumentException("No existe la sala especificada");
        }
        salas.put(sala.getCodigo(), sala);
    }

    public void eliminarSala(String codigo) {
        if (!salas.containsKey(codigo)) {
            throw new IllegalArgumentException("No existe la sala especificada");
        }
        salas.remove(codigo);
    }

    public List<Sala> listarSalas() {
        return new ArrayList<>(salas.values());
    }
}