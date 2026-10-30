
# Analisis de la actividad en base a los criterios establecidos

En este documento se analizará la actividad de acuerdo a los criterios establecidos dentro del documento, primero se empezará por la tabla donde se indicará la base del proyecto.

| Clase candidata | Tipo | Atributos principales | Responsabilidades (métodos) | Justificación |
|---|---|---|---|---|
| `Persona` | Abstracta | nombre, apellido, telefono, correo | `obtenerNombreCompleto()`, `obtenerRol()` | Generaliza los atributos comunes de dueños, veterinarios y recepcionistas. |
| `Dueno` | Concreta | direccion, mascotas | `agregarMascota()`, `obtenerMascotas()` | Representa al propietario de una o varias mascotas. |
| `Veterinario` | Concreta | cedulaProfesional, especialidad | `verificarDisponibilidad()`, `obtenerCitas()` | Profesional responsable de atender las citas. |
| `Recepcionista` | Concreta | turno | `registrarCita()`, `consultarTurno()` | Representa al personal administrativo encargado del registro de citas. |
| `Mascota` | Abstracta | nombre, edad, peso, dueño, historialClinico | `hacerSonido()`, `actualizarPeso()`, `obtenerHistorial()` | Generaliza los atributos y comportamientos de los diferentes tipos de mascotas. |
| `Perro` | Concreta | raza, nivelEnergia | `hacerSonido()`, `toString()` | Especialización de Mascota con características y sonido propios. |
| `Gato` | Concreta | esDomestico | `hacerSonido()`, `toString()` | Especialización de Mascota con comportamiento particular. |
| `AveExotica` | Concreta | longitudAlas | `hacerSonido()`, `toString()` | Representa las aves exóticas atendidas en la clínica. |
| `HistorialClinico` | Concreta | consultas | `registrarConsulta()`, `consultarHistorial()` | Conserva las consultas realizadas a una mascota. |
| `Clinica` | Concreta | nombre, consultorios, veterinarios | `agregarVeterinario()`, `crearConsultorio()`, `obtenerConsultorios()` | Representa el establecimiento, sus espacios físicos y su equipo de veterinarios. |
| `Consultorio` | Concreta | numero | `verificarDisponibilidad()` | Representa un espacio físico de atención dentro de la clínica. |
| `Cita` | Concreta | mascota, veterinario, fechaHora, estado, consultorio, servicio | `agendar()`, `asignarConsultorio()`, `atender()`, `cancelar()` | Coordina la atención programada y sus participantes. |
| `Servicio` | Abstracta | nombre, descripcion | `calcularCosto()` | Generaliza los servicios ofrecidos por la clínica. |
| `ConsultaGeneral` | Concreta | tarifaBase | `calcularCosto()` | Servicio cuyo importe se determina mediante una tarifa base. |
| `Vacunacion` | Concreta | costoVacuna, tarifaAplicacion | `calcularCosto()` | Servicio cuyo costo considera la aplicación y el precio de la vacuna. |
| `Cirugia` | Concreta | complejidad, horasQuirofano, tarifaHora | `calcularCosto()` | Servicio cuyo costo depende de su complejidad y de las horas de quirófano. |
| `Comprobante` | Concreta | folio, fechaEmision, cita, total | `calcularTotal()`, `obtenerResumen()` | Registra el importe cobrado por una cita atendida. |
| `Facturable` | Interfaz | — | `calcularCosto()` | Establece un contrato común para calcular el costo de los servicios mediante polimorfismo. |
