package app;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Consultorio;
import modelo.clinica.EstadoCita;
import modelo.comprobante.Comprobante;
import modelo.mascotas.AveExotica;
import modelo.mascotas.Gato;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.personas.Dueno;
import modelo.personas.Veterinario;
import modelo.servicios.Cirugia;
import modelo.servicios.ConsultaGeneral;
import modelo.servicios.Facturable;
import modelo.servicios.Servicio;
import modelo.servicios.Vacunacion;

/**
 * Clase Main.
 */
public class Main {
    private final Clinica clinica;
    private final Scanner entrada;
    private final List<Dueno> duenos;

    /**
     * Prepara el menú y el registro de dueños de la aplicación.
     * @param clinica clínica que administrará las operaciones
     */
    public Main(Clinica clinica) {
        if (clinica == null) {
            throw new IllegalArgumentException("La clínica es obligatoria.");
        }
        this.clinica = clinica;
        this.entrada = new Scanner(System.in);
        this.duenos = new ArrayList<>();
    }

    /**
     * Método main de inicio.
     * @param argumentos argumentos de consola
     */
    public static void main(String[] argumentos) {
        // Primero creamos la clinica que será nuestro lugar de trabajo
        Clinica clinica = new Clinica("PetCare");

        // Creamos los consultorios de la clinica, en este caso serán 2
        clinica.crearConsultorio(1);
        clinica.crearConsultorio(2);

        // Está será una instancia de Main como tal; Esto con el proposito de ejecutar el código.
        Main aplicacion = new Main(clinica);

        // Esta llamada inicia el menú.
        aplicacion.iniciar();
    }

    /**
     * Repite el menú hasta salir o terminar la entrada de consola.
     */
    public void iniciar() {
        while (true) {
            mostrarMenu();
            try {
                int opcion = leerEntero("Elige una opción: ", 1, 10);
                if (opcion == 10) {
                    System.out.println("Salir.");
                    return;
                }
                ejecutarOpcion(opcion);
            } catch (NoSuchElementException excepcion) {
                System.out.println("\nFin de la entrada. Salir.");
                return;
            } catch (IllegalArgumentException | IllegalStateException excepcion) {
                System.out.println("No se pudo completar la operación: " + excepcion.getMessage());
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n==== PetCare ====");
        System.out.println("1. Registrar dueño y mascota (perro, gato o ave).");
        System.out.println("2. Registrar veterinario y asignarlo a la clínica.");
        System.out.println("3. Agendar una cita.");
        System.out.println("4. Atender o cancelar una cita.");
        System.out.println("5. Generar el comprobante de una cita atendida.");
        System.out.println("6. Consultar el historial clínico y cobros de una mascota.");
        System.out.println("7. Listar todas las mascotas y su sonido (polimorfismo).");
        System.out.println("8. Ver el total cobrado por veterinario.");
        System.out.println("9. Dar de baja una mascota.");
        System.out.println("10. Salir.");
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> registrarDuenoYMascota();
            case 2 -> registrarVeterinario();
            case 3 -> agendarCita();
            case 4 -> atenderOCancelarCita();
            case 5 -> generarComprobante();
            case 6 -> consultarHistorialYCobros();
            case 7 -> listarMascotasYSonidos();
            case 8 -> mostrarTotalesVeterinario();
            case 9 -> darBajaMascota();
            default -> System.out.println("La opción no existe.");
        }
    }

    private void registrarDuenoYMascota() {
        int tipo = leerEntero("Tipo de mascota (1=perro, 2=gato, 3=ave): ", 1, 3);
        if(tipo>3 || tipo<0){
            System.out.println("Valor fuera del rango, saliendo autoticamente.");
        }
        Dueno dueno = seleccionarDueno();
        String nombre = leerTexto("Nombre de la mascota: ");
        int edad = leerEntero("Edad: ", 1, Integer.MAX_VALUE);
        double peso = leerPositivo("Peso: ");
        Mascota mascota = switch (tipo) {
            case 1 -> new Perro(nombre, edad, peso, dueno,
                    leerTexto("Raza: "), leerTexto("Nivel de energía: "));
            case 2 -> new Gato(nombre, edad, peso, dueno,
                    leerEntero("¿Es doméstico? (1=sí, 2=no): ", 1, 2) == 1);
            // Se decidio así para que cualquier número sin importar que no sea 3 lo mande como AveExotica.
           case 3 -> new AveExotica(nombre, edad, peso, dueno,
                    leerPositivo("Longitud de las alas: "));
           default -> null;
        };
        clinica.registrarMascota(mascota);
        System.out.println("Mascota registrada: " + mascota);
    }

    // Método utilizado para las acciones del Dueño.
    private Dueno seleccionarDueno() {
        System.out.println("\n==== Dueños ====");
        System.out.println("0. Registrar un dueño nuevo.");
        // Muestra todos los dueños ya registrados, si es 0 no muestra ninguno
        for (int i = 0; i < duenos.size(); i++) {
            Dueno dueno = duenos.get(i);
            System.out.println((i + 1) + ". " + dueno.getNombre() + " " + dueno.getApellido());
        }
        // Contrapción para seleccionar uno de los dueños ya registrados
        int opcion = leerEntero("Elige opción: ", 0, duenos.size());
        // Como es una lista se le resta -1 para obtener el índice.
        if (opcion != 0) {
            return duenos.get(opcion - 1);
        }
        //Opción donde se registrar el dueño, "0", se asume que no se eligio ningún dueño
        Dueno dueno = new Dueno(leerTexto("Nombre del dueño: "), leerTexto("Apellido: "),
                leerTexto("Teléfono: "), leerTexto("Correo: "), leerTexto("Dirección: "));
        duenos.add(dueno);
        System.out.println("Dueño registrado: " + dueno.getNombre() + " " + dueno.getApellido());
        return dueno;
    }

    // Método utilizado para las acciones del Veterinario
    private void registrarVeterinario() {
        // Registrar un veterinario y agregarlo a la clinica existente
        Veterinario veterinario = new Veterinario(leerTexto("Nombre del veterinario: "),
                leerTexto("Apellido: "), leerTexto("Teléfono: "), leerTexto("Correo: "),
                leerTexto("Cédula profesional: "), leerTexto("Especialidad: "));
        clinica.agregarVeterinario(veterinario);
    }

    private void agendarCita() {
        // Validación, tiene que existir en la clinica, mascotas veterinarios y consultorios para poder registrar una cita, si uno falta no se puede.
        if (clinica.getMascotas().isEmpty() || clinica.getVeterinarios().isEmpty()
                || clinica.getConsultorios().isEmpty()) {
            System.out.println("Primero registra mascotas, veterinarios y consultorios.");
            return;
        }

        // Aquí se usan los métodos reutilizables para listar mascotas, veterinarios y consultorios.
        Mascota mascota = seleccionar(clinica.getMascotas(), "Mascotas");
        Veterinario veterinario = seleccionar(clinica.getVeterinarios(), "Veterinarios");
        Consultorio consultorio = seleccionar(clinica.getConsultorios(), "Consultorios");

        // Se recibe la fecha a agendar
        LocalDateTime horario = leerFechaHora();

        // Y el servicio a hacer en la cita
        Servicio servicio = leerServicio();

        // Se registra la cita en el sistema
        Cita cita = new Cita(mascota, veterinario, horario, servicio, consultorio);
        clinica.agendarCita(cita);

        // Y esto se hace de está manera porqué la validación se hace en los métodos de clinica y cita; Así decimos el resumen de lo registrado.
        if (clinica.getCitas().contains(cita)) {
            System.out.println("Cita agendada: " + cita.getFolio());
            System.out.println("Costo de " + servicio.getNombre() + ": $"
                    + servicio.calcularCosto().toPlainString());
            System.out.println("Citas registradas: " + clinica.getCitas().size());
        }
    }

    // Método reutilizable
    private Servicio leerServicio() {
        // Se imprime la información de cada servicio, esto es para "setear" el entorno.
        int opcion = leerEntero("Servicio (1=consulta, 2=vacunación, 3=cirugía): ", 1, 3);
        return switch (opcion) {
            case 1 -> new ConsultaGeneral(leerImporte("Tarifa de consulta: "));
            case 2 -> new Vacunacion(leerImporte("Costo de la vacuna: "),
                    leerImporte("Tarifa de aplicación: "));
            default -> new Cirugia(leerPositivo("Factor de complejidad (1=base, 1.5=+50%): "),
                    leerPositivo("Horas de quirófano: "), leerImporte("Tarifa por hora: "));
        };
    }

    // Método para buscar, seleccionar, y cambiar de estado una cita.
    private void atenderOCancelarCita() {
        // Buscamos una cita
        Cita cita = seleccionarCita();
        if (cita == null) {
            return;
        }
        // Utilizamos los métodos reutilizables para leer la opción
        int opcion = leerEntero("Acción (1=atender, 2=cancelar): ", 1, 2);
        // las opciones en if sobre los cambios de estado
        if (opcion == 2) {
            cita.cancelar();
        } else if (cita.getEstado() == EstadoCita.PROGRAMADA) {
            String observaciones = leerTexto("Observaciones de la atención: ");
            cita.atender();
            if (cita.getEstado() == EstadoCita.ATENDIDA) {
                cita.registrarObservaciones(observaciones);
            }
            // Si no es es 1; dado el "minimo" y "máximo" por lo que se hace la atención.
        } else {
            cita.atender();
        }
        System.out.println("Estado de " + cita.getFolio() + ": " + cita.getEstado());
    }

    // Método reutilizable para listar y seleccionar las citas de una clinica
    private Cita seleccionarCita() {
        //  Validamos si hay citas.
        if (clinica.getCitas().isEmpty()) {
            System.out.println("No hay citas registradas.");
            return null;
        }
        // Mostramos todas las citas con toString
        System.out.println("\n==== Citas ====");
        for (Cita cita : clinica.getCitas()) {
            System.out.println(cita);
        }

        // Usamos el método existente y mostramos la información, da más información de una cita en especifico
        return clinica.buscarCita(leerTexto("Folio de la cita: "));
    }

    private void generarComprobante() {
        // Este método valida si existe la cita seleccionada y usa los métodos pre-existentes
        Cita cita = seleccionarCita();
        if (cita == null) {
            return;
        }
        Comprobante comprobante = cita.generarComprobante();
        if (comprobante != null) {
            System.out.println(comprobante.imprimir());
        }
    }

    private void consultarHistorialYCobros() {
        // Usando el método reutilizable que lista una clase se listan las Mascotas y se ponen como opciones
        Mascota mascota = seleccionar(clinica.getMascotas(), "Mascotas");
        if (mascota == null) {
            return;
        }
        // Una vez se escogio la mascota se muestra su información.
        System.out.println("\n==== Historial clínico de " + mascota.getNombre() + " ====");
        if (mascota.getHistorial() == null) {
            System.out.println("La mascota no tiene historial activo.");
        } else if (mascota.getHistorial().getAtenciones().isEmpty()) {
            System.out.println("No hay atenciones registradas.");
        } else {
            mascota.getHistorial().listarAtenciones();
        }
        List<Comprobante> cobros = clinica.consultarCobrosPorMascota(mascota);
        System.out.println("\n==== Comprobantes de " + mascota.getNombre() + " ====");
        if (cobros.isEmpty()) {
            System.out.println("No hay cobros registrados.");
        }
        for (Comprobante comprobante : cobros) {
            System.out.println(comprobante.imprimir());
        }
    }

    private void listarMascotasYSonidos() {
        // Se obtiene la lista de las mascotas con el proposito de recorrerlas para imprimir sus sonidos.
        List<Mascota> mascotas = clinica.getMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return;
        }
        for (Mascota mascota : mascotas) {
            System.out.println(mascota + " | Sonido: " + mascota.hacerSonido());
        }
    }

    private void mostrarTotalesVeterinario() {
        // Total se usa como BigDecimal por presición
        BigDecimal total = BigDecimal.ZERO;
        // Se muestra el total por veterinario recorriendo cada veterinario y usando los métodos ya existentes.
        System.out.println("\n==== Total cobrado por veterinario ====");
        for (Veterinario veterinario : clinica.getVeterinarios()) {
            BigDecimal cobrado = clinica.consultarTotalCobradoPorVeterinario(veterinario);
            System.out.println(veterinario.getNombre() + " " + veterinario.getApellido()
                    + ": " + cobrado.toPlainString());
            total = total.add(cobrado);
        }
        System.out.println("Total cobrado: " + total.toPlainString());
        mostrarCostosServicios();
    }

    private void mostrarCostosServicios() {
        // Se leen los servicios.
        List<Facturable> servicios = new ArrayList<>();
        for (Cita cita : clinica.getCitas()) {
            if (cita.getEstado() != EstadoCita.CANCELADA) {
                servicios.add(cita.getServicio());
            }
        }
        BigDecimal total = BigDecimal.ZERO;
        for (Facturable servicio : servicios) {
            total = total.add(servicio.calcularCosto());
        }

        // Se parsea el total para que sea texto y se pueda concatenar.
        System.out.println("Costo calculado de servicios no cancelados: " + total.toPlainString());
        System.out.println("Este cálculo incluye citas pendientes; no es el total cobrado.");
    }

    private void darBajaMascota() {
        // Usando los cambios hechos a la aplicación esto se hace de manera sencilla seleccionandola y usando los métodos.
        Mascota mascota = seleccionar(clinica.getMascotas(), "Mascotas");
        if (mascota == null) {
            return;
        }
        clinica.darBajaMascota(mascota);
        System.out.println("Mascota dada de baja: " + mascota.getNombre());
    }

    // Una ingeniosa solución de la IA, "T" es un tipo generico, acepta por ejemplo: "Cita", "Clinica", "Comprobante"; porqué son tipos
    // personalizados, por lo que los acepta como tipos genericos.

    // Método altamente reutilizable para usar los getters de todas las clases.
    private <T> T seleccionar(List<T> opciones, String titulo) {
        if (opciones.isEmpty()) {
            System.out.println("No hay registros en " + titulo + ".");
            return null;
        }
        System.out.println("\n==== " + titulo + " ====");
        for (int i = 0; i < opciones.size(); i++) {
            System.out.println((i + 1) + ". " + opciones.get(i));
        }
        int opcion = leerEntero("Elige opción: ", 1, opciones.size());
        return opciones.get(opcion - 1);
    }

    // Este metodo es muy importante, básicamente permite ser un "input", es para Strings
    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = entrada.nextLine().trim();
            if (!texto.isBlank()) {
                return texto;
            }
            System.out.println("El dato no puede estar vacío.");
        }
    }

    // Este metodo es muy importante, básicamente permite ser un "input", es para enteros +/-
    private int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            try {
                int valor = Integer.parseInt(leerTexto(mensaje));
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException excepcion) {
                System.out.println("Debes escribir un número entero.");
                continue;
            }
            System.out.println("Elige un valor entre " + minimo + " y " + maximo + ".");
        }
    }

    // Este metodo es muy importante, básicamente permite ser un "input", solo es para positivos, con el proposito de validar.
    private double leerPositivo(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(leerTexto(mensaje).replace(',', '.'));
                if (Double.isFinite(valor) && valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException excepcion) {
                System.out.println("Debes escribir un número.");
                continue;
            }
            System.out.println("El valor debe ser finito y mayor que cero.");
        }
    }

    // Este metodo es muy importante, básicamente permite ser un "input", para leer BigDecimal, cantidades monetarias precisas.
    private BigDecimal leerImporte(String mensaje) {
        while (true) {
            try {
                BigDecimal importe = new BigDecimal(leerTexto(mensaje).replace(',', '.'));
                if (importe.compareTo(BigDecimal.ZERO) >= 0) {
                    return importe;
                }
            } catch (NumberFormatException excepcion) {
                System.out.println("Debes escribir un importe numérico.");
                continue;
            }
            System.out.println("El importe no puede ser negativo.");
        }
    }

    // Este metodo es muy importante, básicamente permite ser un "input", es para definir una fecha exacta, el formato es AAAA-MM-DD HH:MM
    private LocalDateTime leerFechaHora() {
        while (true) {
            try {
                String texto = leerTexto("Fecha y hora (AAAA-MM-DD HH:MM): ");
                return LocalDateTime.parse(texto.replace(' ', 'T'));
            } catch (DateTimeParseException excepcion) {
                System.out.println("Fecha u hora inválida. Ejemplo: 2026-11-02 09:00.");
            }
        }
    }
}
