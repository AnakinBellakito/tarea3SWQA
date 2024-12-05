package com.app;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import com.entity.Reserva;
import com.entity.Sala;
import com.entity.Usuario;
import com.gestor.GestorReservas;
import com.gestor.GestorSalas;
import com.gestor.GestorUsuarios;


public class SistemaReservas {
    private final Scanner scanner;
    private final GestorSalas gestorSalas;
    private final GestorUsuarios gestorUsuarios;
    private final GestorReservas gestorReservas;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public SistemaReservas() {
        this.scanner = new Scanner(System.in);
        this.gestorSalas = new GestorSalas();
        this.gestorUsuarios = new GestorUsuarios();
        this.gestorReservas = new GestorReservas(gestorSalas, gestorUsuarios);
    }

    public void ejecutar() {
        inicializarSistema();
        boolean continuar = true;

        while (continuar) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    gestionarSalas();
                    break;
                case 2:
                    gestionarUsuarios();
                    break;
                case 3:
                    gestionarReservas();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
        cerrarSistema();
    }

    private void cerrarSistema() {
        scanner.close();
        System.out.println("Cerrando el sistema...");
        System.out.println("¡Gracias por usar el Sistema de Reservas!");
    }

    private void inicializarSistema() {
        System.out.println("Sistema de Reservas inicializado correctamente.");
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE RESERVAS DE SALAS ===");
        System.out.println("1. Gestión de Salas");
        System.out.println("2. Gestión de Usuarios");
        System.out.println("3. Gestión de Reservas");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void gestionarSalas() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE SALAS ===");
            System.out.println("1. Agregar Sala");
            System.out.println("2. Listar Salas");
            System.out.println("3. Actualizar Sala");
            System.out.println("4. Eliminar Sala");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            switch (leerOpcion()) {
                case 1:
                    agregarSala();
                    break;
                case 2:
                    listarSalas();
                    break;
                case 3:
                    actualizarSala();
                    break;
                case 4:
                    eliminarSala();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void gestionarUsuarios() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE USUARIOS ===");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Actualizar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            switch (leerOpcion()) {
                case 1:
                    agregarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    actualizarUsuario();
                    break;
                case 4:
                    eliminarUsuario();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void gestionarReservas() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n=== GESTIÓN DE RESERVAS ===");
            System.out.println("1. Crear Reserva");
            System.out.println("2. Listar Reservas");
            System.out.println("3. Actualizar Reserva");
            System.out.println("4. Eliminar Reserva");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            switch (leerOpcion()) {
                case 1:
                    crearReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    actualizarReserva();
                    break;
                case 4:
                    eliminarReserva();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Métodos para gestión de salas
    private void agregarSala() {
        System.out.println("\n=== AGREGAR SALA ===");
        System.out.print("Ingrese código de sala: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese nombre de sala: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ubicación: ");
        String ubicacion = scanner.nextLine();

        try {
            Sala sala = new Sala(codigo, nombre, ubicacion);
            gestorSalas.agregarSala(sala);
            System.out.println("Sala agregada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private  void listarSalas() {
        System.out.println("\n=== LISTADO DE SALAS ===");
        List<Sala> salas = gestorSalas.listarSalas();
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas.");
            return;
        }
        for (Sala sala : salas) {
            System.out.printf("Código: %s | Nombre: %s | Ubicación: %s | Estado: %s%n",
                    sala.getCodigo(),
                    sala.getNombre(),
                    sala.getUbicacion(),
                    sala.isReservada() ? "Reservada" : "Disponible");
        }
    }

    private  void actualizarSala() {
        System.out.println("\n=== ACTUALIZAR SALA ===");
        System.out.print("Ingrese código de sala a actualizar: ");
        String codigo = scanner.nextLine();
        
        Sala sala = gestorSalas.obtenerSala(codigo);
        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }

        System.out.print("Nuevo nombre (Enter para mantener actual [" + sala.getNombre() + "]): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            sala.setNombre(nombre);
        }

        System.out.print("Nueva ubicación (Enter para mantener actual [" + sala.getUbicacion() + "]): ");
        String ubicacion = scanner.nextLine();
        if (!ubicacion.isEmpty()) {
            sala.setUbicacion(ubicacion);
        }

        try {
            gestorSalas.actualizarSala(sala);
            System.out.println("Sala actualizada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarSala() {
        System.out.println("\n=== ELIMINAR SALA ===");
        System.out.print("Ingrese código de sala a eliminar: ");
        String codigo = scanner.nextLine();

        try {
            gestorSalas.eliminarSala(codigo);
            System.out.println("Sala eliminada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Métodos para gestión de usuarios
    private void agregarUsuario() {
        System.out.println("\n=== AGREGAR USUARIO ===");
        System.out.print("Ingrese identificador: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Ingrese descripción: ");
        String descripcion = scanner.nextLine();

        try {
            Usuario usuario = new Usuario(id, nombre, departamento, descripcion);
            gestorUsuarios.agregarUsuario(usuario);
            System.out.println("Usuario agregado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        System.out.println("\n=== LISTADO DE USUARIOS ===");
        List<Usuario> usuarios = gestorUsuarios.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.printf("ID: %s | Nombre: %s | Departamento: %s | Descripción: %s%n",
                    usuario.getIdentificador(),
                    usuario.getNombre(),
                    usuario.getDepartamento(),
                    usuario.getDescripcion());
        }
    }

    private void actualizarUsuario() {
        System.out.println("\n=== ACTUALIZAR USUARIO ===");
        System.out.print("Ingrese identificador del usuario a actualizar: ");
        String id = scanner.nextLine();
        
        Usuario usuario = gestorUsuarios.obtenerUsuario(id);
        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (Enter para mantener actual [" + usuario.getNombre() + "]): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            usuario.setNombre(nombre);
        }

        System.out.print("Nuevo departamento (Enter para mantener actual [" + usuario.getDepartamento() + "]): ");
        String departamento = scanner.nextLine();
        if (!departamento.isEmpty()) {
            usuario.setDepartamento(departamento);
        }

        System.out.print("Nueva descripción (Enter para mantener actual [" + usuario.getDescripcion() + "]): ");
        String descripcion = scanner.nextLine();
        if (!descripcion.isEmpty()) {
            usuario.setDescripcion(descripcion);
        }

        try {
            gestorUsuarios.actualizarUsuario(usuario);
            System.out.println("Usuario actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarUsuario() {
        System.out.println("\n=== ELIMINAR USUARIO ===");
        System.out.print("Ingrese identificador del usuario a eliminar: ");
        String id = scanner.nextLine();

        try {
            gestorUsuarios.eliminarUsuario(id);
            System.out.println("Usuario eliminado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Métodos para gestión de reservas
    private void crearReserva() {
        System.out.println("\n=== CREAR RESERVA ===");
        
        // Mostrar salas disponibles
        List<Sala> salas = gestorSalas.listarSalas();
        if (salas.isEmpty()) {
            System.out.println("No hay salas disponibles para reservar.");
            return;
        }

        System.out.println("Salas disponibles:");
        for (Sala sala : salas) {
            if (!sala.isReservada()) {
                System.out.printf("Código: %s | Nombre: %s | Ubicación: %s%n",
                        sala.getCodigo(), sala.getNombre(), sala.getUbicacion());
            }
        }

        System.out.print("Ingrese código de sala: ");
        String codigoSala = scanner.nextLine();

        // Mostrar usuarios
        List<Usuario> usuarios = gestorUsuarios.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados para hacer reservas.");
            return;
        }

        System.out.println("Usuarios registrados:");
        for (Usuario usuario : usuarios) {
            System.out.printf("ID: %s | Nombre: %s%n", 
                    usuario.getIdentificador(), usuario.getNombre());
        }

        System.out.print("Ingrese ID de usuario: ");
        String idUsuario = scanner.nextLine();

        System.out.print("Ingrese fecha y hora (formato dd/MM/yyyy HH:mm): ");
        String fechaStr = scanner.nextLine();

        System.out.print("Ingrese detalle de la reserva: ");
        String detalle = scanner.nextLine();

        try {
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, formatter);
            gestorReservas.crearReserva(generarIdReserva(), codigoSala, idUsuario, fecha, detalle);
            System.out.println("Reserva creada exitosamente.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarReservas() {
        System.out.println("\n=== LISTADO DE RESERVAS ===");
        List<Reserva> reservas = gestorReservas.listarReservas();
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }
        for (Reserva reserva : reservas) {
            System.out.printf("ID: %s | Sala: %s | Usuario: %s | Fecha: %s | Detalle: %s%n",
                    reserva.getId(),
                    reserva.getSala().getNombre(),
                    reserva.getUsuario().getNombre(),
                    reserva.getFecha().format(formatter),
                    reserva.getDetalle());
        }
    }

    private void actualizarReserva() {
        System.out.println("\n=== ACTUALIZAR RESERVA ===");
        System.out.print("Ingrese ID de la reserva a actualizar: ");
        String id = scanner.nextLine();
        
        Reserva reserva = gestorReservas.obtenerReserva(id);
        if (reserva == null) {
            System.out.println("Reserva no encontrada.");
            return;
        }

        System.out.println("Datos actuales de la reserva:");
        System.out.printf("Sala: %s | Usuario: %s | Fecha: %s | Detalle: %s%n",
                reserva.getSala().getNombre(),
                reserva.getUsuario().getNombre(),
                reserva.getFecha().format(formatter),
                reserva.getDetalle());

        System.out.print("Nueva fecha y hora (Enter para mantener actual) [" + 
                        reserva.getFecha().format(formatter) + "]: ");
        String fechaStr = scanner.nextLine();
        
        System.out.print("Nuevo detalle (Enter para mantener actual) [" + 
                        reserva.getDetalle() + "]: ");
        String detalle = scanner.nextLine();

        try {
            if (!fechaStr.isEmpty()) {
                LocalDateTime nuevaFecha = LocalDateTime.parse(fechaStr, formatter);
                reserva.setFecha(nuevaFecha);
            }
            
            if (!detalle.isEmpty()) {
                reserva.setDetalle(detalle);
            }

            gestorReservas.actualizarReserva(reserva);
            System.out.println("Reserva actualizada exitosamente.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarReserva() {
        System.out.println("\n=== ELIMINAR RESERVA ===");
        System.out.print("Ingrese ID de la reserva a eliminar: ");
        String id = scanner.nextLine();

        try {
            gestorReservas.eliminarReserva(id);
            System.out.println("Reserva eliminada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Métodos auxiliares
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String generarIdReserva() {
        return "R" + System.currentTimeMillis();
    }

    // Método para limpiar la pantalla (opcional)
    private void limpiarPantalla() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si falla la limpieza de pantalla, simplemente imprimimos líneas en blanco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
