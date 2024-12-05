package com;

import com.app.SistemaReservas;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Reservas de Salas...");
        System.out.println("=========================================");
        
        try {
            // Create instance of the reservation system and run it
            SistemaReservas sistema = new SistemaReservas();
            sistema.ejecutar();
            
        } catch (Exception e) {
            System.err.println("Error crítico al iniciar el sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}