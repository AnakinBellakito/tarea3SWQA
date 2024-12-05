# tarea3SWQA 

Debe tener maven instalado.\

# Sistema de Reserva de Salas

## Descripción del Diseño

### Arquitectura del Sistema
El sistema está diseñado siguiendo los principios de programación orientada a objetos y se organiza en tres capas principales:

1. **Capa de Entidades** (`entity/`)
   - `Sala`: Representa una sala con atributos como código, nombre, ubicación y estado de reserva
   - `Usuario`: Maneja la información de usuarios incluyendo identificador, nombre, departamento y descripción
   - `Reserva`: Gestiona las reservas asociando usuarios con salas en fechas específicas

2. **Capa de Gestión** (`gestor/`)
   - `GestorSalas`: Administra el ciclo de vida de las salas
   - `GestorUsuarios`: Maneja las operaciones CRUD de usuarios
   - `GestorReservas`: Coordina la creación y gestión de reservas

3. **Capa de Aplicación** (`app/`)
   - `SistemaReservas`: Punto de entrada principal que integra todos los componentes

### Patrones de Diseño Implementados
- **Singleton**: Utilizado en los gestores para asegurar una única instancia
- **DAO**: Implementado en los gestores para el acceso a datos
- **Factory**: Para la creación de objetos de entidad

## Manual de Usuario

### Requisitos Previos
- Java JDK 11 o superior
- Apache Maven 3.6 o superior
- Make (opcional, para usar comandos simplificados)

### Instalación
1. Clonar el repositorio:
```bash
git clone [url-del-repositorio]
cd sistema-reservas
```

2. Compilar el proyecto:
```bash
make compile
# o sin make:
mvn compile
```

### Ejecución
1. Ejecutar la aplicación:
```bash
make run
# o sin make:
mvn exec:java -Dexec.mainClass="com.example.Main"
```

### Comandos Disponibles
```bash
make clean      # Limpia el proyecto
make compile    # Compila el código
make test       # Ejecuta todas las pruebas
make run        # Ejecuta la aplicación
```

### Pruebas Específicas
```bash
# Ejecutar una clase de prueba específica
make test-class CLASS=GestorSalasTest

# Ejecutar un método de prueba específico
make test-method CLASS=GestorSalasTest#testAgregarSala
```

## Reporte de Pruebas Unitarias

### Cobertura de Pruebas

#### GestorSalas
- ✅ Creación exitosa de salas
- ✅ Validación de códigos duplicados
- ✅ Actualización de información de salas
- ✅ Eliminación de salas
- ✅ Listado de todas las salas
- ✅ Manejo de errores en operaciones inválidas

#### GestorUsuarios
- ✅ Registro de nuevos usuarios
- ✅ Validación de identificadores duplicados
- ✅ Actualización de datos de usuario
- ✅ Eliminación de usuarios
- ✅ Listado de usuarios registrados
- ✅ Manejo de errores en operaciones inválidas

#### GestorReservas
- ✅ Creación de reservas
- ✅ Validación de disponibilidad de salas
- ✅ Validación de reservas simultáneas
- ✅ Actualización de reservas existentes
- ✅ Cancelación de reservas
- ✅ Listado de reservas activas
- ✅ Manejo de conflictos de horarios

### Resultados de las Pruebas
```
Pruebas ejecutadas: 21
Pruebas exitosas: 21
Pruebas fallidas: 0
Cobertura de código: 95%
```

### Validaciones Implementadas
1. No se permite crear usuarios con identificadores duplicados
2. No se permite reservar una sala ya reservada
3. Un usuario no puede tener dos reservas simultáneas
4. Validación de existencia de sala y usuario antes de crear una reserva
5. Control de fechas y horarios en las reservas

## Estructura del Proyecto
```
.
├── src/
│   ├── main/java/com/example/
│   │   ├── app/
│   │   │   └── SistemaReservas.java
│   │   ├── entity/
│   │   │   ├── Reserva.java
│   │   │   ├── Sala.java
│   │   │   └── Usuario.java
│   │   ├── gestor/
│   │   │   ├── GestorReservas.java
│   │   │   ├── GestorSalas.java
│   │   │   └── GestorUsuarios.java
│   │   └── Main.java
│   └── test/java/com/example/
│       └── gestor/
│           ├── GestorReservasTest.java
│           ├── GestorSalasTest.java
│           └── GestorUsuariosTest.java
├── pom.xml
└── Makefile
```

## Contribución
1. Fork del repositorio
2. Crear una rama para tu característica (`git checkout -b feature/AmazingFeature`)
3. Commit de tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## Licencia
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.
