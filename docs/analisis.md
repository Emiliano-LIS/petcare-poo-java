
# Análisis de la actividad con base en los criterios establecidos

Este documento describe las clases del modelo, sus responsabilidades, ejemplos de objetos
y las decisiones de diseño de la implementación actual de PetCare.

## Clases candidatas del modelo

| Clase candidata | Tipo | Atributos principales | Responsabilidades (métodos) | Justificación |
|---|---|---|---|---|
| `Persona` | Abstracta | nombre, apellido, telefono, correo | `actualizarContacto()`, `describirRol()`, validación de textos | Generaliza los datos de contacto y el contrato para describir el rol de dueños y personal. |
| `Dueno` | Concreta | direccion, mascotas | `agregarMascota()`, `eliminarMascota()`, `getMascotas()`, `listarMascotas()`, `describirRol()` | Representa al propietario y mantiene las referencias a sus mascotas registradas. |
| `Veterinario` | Concreta | cedulaProfesional, especialidad | `getCedulaProfesional()`, `getEspecialidad()`, `setEspecialidad()`, `describirRol()` | Identifica al profesional mediante una cédula que no cambia; la clínica administra su disponibilidad y evita cédulas duplicadas. |
| `Recepcionista` | Concreta | turno | `registrarCita(Cita, Clinica)`, `getTurno()`, `describirRol()` | Delega el registro de citas en la clínica, que conoce la agenda completa. |
| `Mascota` | Abstracta | nombre, edad, peso, dueno, historial | `hacerSonido()`, `setEdad()`, `setPeso()`, `getHistorial()`, `crearHistorial()`, `eliminarHistorial()` | Reúne los datos comunes, valida edad y peso y administra el historial propio de cada mascota. |
| `Perro` | Concreta | raza, nivelEnergia | `hacerSonido()`, `toString()` | Especialización de Mascota con características y sonido propios. |
| `Gato` | Concreta | esDomestico | `hacerSonido()`, `toString()` | Especialización de Mascota con comportamiento particular. |
| `AveExotica` | Concreta | longitudAlas | `hacerSonido()`, `toString()` | Representa las aves exóticas atendidas en la clínica. |
| `HistorialClinico` | Concreta | atenciones | `registrarAtencion()`, `getAtenciones()`, `listarAtenciones()` | Conserva citas atendidas de su mascota y evita registros duplicados o ajenos. |
| `Clinica` | Concreta | nombre, consultorios, veterinarios, citas, mascotas | `crearConsultorio()`, `agregarVeterinario()`, `registrarMascota()`, `darBajaMascota()`, `agendarCita()`, `buscarCita(String)`, `buscarCita(LocalDate)`, `validarDisponibilidad()`, `consultarTotalCobradoPorVeterinario()`, `consultarCobrosPorMascota()` | Coordina el registro activo, la agenda, los conflictos de horario y las consultas de cobros. |
| `Consultorio` | Concreta | numero | `getNumero()`, `toString()` | Identifica un espacio creado por la clínica; su número queda fijo y la clínica comprueba su disponibilidad. |
| `Cita` | Concreta | contadorCitas, folio, fechaHora, estado, mascota, veterinario, consultorio, servicio, observaciones, comprobante | `atender()`, `cancelar()`, `registrarObservaciones()`, `generarComprobante()`, `getEstado()` | Representa una atención con participantes y horario fijos; controla su estado, incorpora la atención al historial y conserva su comprobante. |
| `Servicio` | Abstracta | nombre | `getNombre()`, `validarImporte()`, `redondearCosto()`; contrato `calcularCosto()` de `Facturable` | Comparte el nombre y las validaciones monetarias; las clases concretas implementan el cálculo. |
| `ConsultaGeneral` | Concreta | tarifaBase | `calcularCosto()` | Servicio cuyo importe se determina mediante una tarifa base. |
| `Vacunacion` | Concreta | costoVacuna, tarifaAplicacion | `calcularCosto()` | Servicio cuyo costo considera la aplicación y el precio de la vacuna. |
| `Cirugia` | Concreta | complejidad, horasQuirofano, tarifaPorHora | `calcularCosto()` | Multiplica la tarifa por hora, las horas de quirófano y el factor de complejidad. |
| `Comprobante` | Concreta | contadorComprobantes, folio, total, cita | `Comprobante(Cita)`, `getFolio()`, `getTotal()`, `getCita()`, `imprimir()` | Calcula y conserva el importe al emitirse para una cita atendida; folio, total y referencia a la cita quedan fijos. |
| `Facturable` | Interfaz | — | `calcularCosto()` | Establece un contrato común para calcular el costo de los servicios mediante polimorfismo. |
| `EstadoCita` | Enumeración | PROGRAMADA, ATENDIDA, CANCELADA | Representar los estados permitidos de una cita | Evita representar los estados mediante textos arbitrarios. |
| `Main` | Concreta | clinica, entrada, duenos | `main()`, `iniciar()`, `registrarDuenoYMascota()`, `agendarCita()`, `consultarHistorialYCobros()`, `mostrarTotalesVeterinario()` | Gestiona la interacción por consola y delega las operaciones del negocio en el modelo. |


## Tres objetos de ejemplo

| Objeto | Clase | Valores de ejemplo |
|---|---|---|
| `ana` | `Dueno` | Nombre: Ana; apellido: Pérez; teléfono: 5551234567; correo: ana@example.com; dirección: Calle Norte 10. |
| `sol` | `Perro` | Nombre: Sol; edad: 3; peso: 12.5; dueño: Ana; raza: Mestizo; energía: Alto. |
| `luis` | `Veterinario` | Nombre: Luis; apellido: García; teléfono: 5551112233; correo: luis@example.com; cédula: VET-001; especialidad: Medicina general. |

## Clases candidatas descartadas

- **Telefono:** se representa mediante el atributo `telefono` de tipo `String` en `Persona`. El alcance solo requiere guardar y consultar el dato, sin operaciones propias que justifiquen otra clase.
- **Direccion:** se representa mediante el atributo `direccion` de tipo `String` en `Dueno`. No se necesitan operaciones independientes sobre calles, ciudades o códigos postales para resolver el caso de estudio.

## Elección de Facturable como interfaz

`Facturable` se conserva en el modelo como interfaz porque define el contrato `calcularCosto()` que deben ofrecer los servicios. Esta es una elección de tipo, por lo que no se cuenta entre las clases candidatas descartadas.

## Justificación de relaciones

- **Herencia:** Dueno, Recepcionista, Veterinario heredan de Persona porque comparten nombre, apellido, teléfono y correo. Cada clase añade sus datos propios y describe su rol mediante `describirRol()` un método que retorna un String.
- **Herencia:** Perro, Gato, AveExotica heredan de Mascota porque comparten nombre, edad, peso, dueño e historial. Cada especie añade sus características y proporciona su propio `hacerSonido()` que es parte del polimorfismo.
- **Herencia:** ConsultaGeneral, Vacunacion, Cirugia heredan de Servicio porque representan servicios de la clínica y comparten su nombre y las validaciones de importes. Cada servicio calcula el costo de acuerdo con su fórmula.
- **Interfaz:** Servicio implementa Facturable; ConsultaGeneral, Vacunacion, Cirugia proporcionan `calcularCosto()`. El contrato permite reunirlas en una `List<Facturable>` y sumar costos sin preguntar qué tipo de servicio es cada objeto.
- **Asociación entre Dueno y Mascota:** cada mascota referencia a su dueño y este conserva una lista de sus mascotas. Dar de baja una mascota no elimina al dueño; se retira la mascota de su lista.
- **Asociación de Clinica con Mascota y Cita:** la clínica mantiene el registro de mascotas activas y la agenda. Al dar de baja una mascota, cancela sus citas programadas y conserva las atendidas y sus comprobantes para consultar cobros anteriores.
- **Asociación de Cita con Mascota, Veterinario y Consultorio:** una cita relaciona participantes y un espacio que ya existen. Cancelarla no elimina a la mascota, al veterinario ni al consultorio; sus referencias se fijan al construirla.
- **Asociación entre Cita y Servicio:** la cita referencia el servicio solicitado para calcular su costo. Cancelar la cita no destruye el servicio, que puede existir como un objeto independiente.
- **Asociación entre HistorialClinico y Cita:** el historial conserva referencias a las citas atendidas de su mascota. Retirar el historial no elimina esas citas de la agenda ni sus comprobantes.
- **Agregación entre Clinica y Veterinario:** la clínica recibe veterinarios ya creados y los incorpora a su equipo. Un veterinario puede existir independientemente de esa clínica y trabajar en otra.
- **Composición entre Clinica y Consultorio:** la clínica crea sus consultorios internamente y controla su registro. Los consultorios no se agregan desde fuera ni se comparten entre clínicas; su ciclo de vida en el sistema depende de la clínica propietaria.
- **Composición entre Mascota e HistorialClinico:** cada mascota crea y posee su historial. Durante su baja se retira esa referencia; si no quedan otras referencias al historial, el recolector de basura puede recuperar su memoria.
- **Composición entre Cita y Comprobante:** una cita atendida genera y conserva su comprobante. Solicitarlo otra vez devuelve el mismo objeto, cuyo folio, total y referencia a la cita no se pueden reasignar.
- **Asociación de Main con Clinica y Dueno:** el menú conserva la clínica que administra y los dueños registrados durante la ejecución. Un mismo dueño puede seleccionarse para registrar varias mascotas sin crearlo de nuevo.
- **Dependencia de Recepcionista con Clinica y Cita:** `registrarCita(cita, clinica)` utiliza los objetos recibidos y delega el registro en la agenda de la clínica. No crea ni conserva una agenda propia.
