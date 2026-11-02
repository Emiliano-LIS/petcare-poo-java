package regresion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;
import modelo.clinica.Consultorio;
import modelo.clinica.EstadoCita;
import modelo.mascotas.AveExotica;
import modelo.mascotas.Gato;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.personas.Dueno;
import modelo.personas.Persona;
import modelo.personas.Recepcionista;
import modelo.personas.Veterinario;
import modelo.servicios.Cirugia;
import modelo.servicios.ConsultaGeneral;
import modelo.servicios.Facturable;
import modelo.servicios.Vacunacion;

/** Pruebas de regresión del punto 1, ejecutables sin bibliotecas externas. */
public class PuntoUnoTest {
    private static final BigDecimal TARIFA = new BigDecimal("300.00");
    private static final LocalDateTime HORARIO = LocalDateTime.of(2026, 11, 5, 10, 0);
    private static int comprobaciones;

    /** Ejecuta las comprobaciones y falla con AssertionError si hay una regresión.
     * @param argumentos argumentos de consola
     */
    public static void main(String[] argumentos) {
        probarImportesInvalidos();
        probarImportesCero();
        probarFactoresInvalidos();
        probarRedondeoYTotal();
        probarEdadYPeso();
        probarPolimorfismo();
        probarDisponibilidad();
        probarRegistroRecepcion();
        System.out.println("Correcto: " + comprobaciones + " comprobaciones.");
    }

    private static void probarImportesInvalidos() {
        ConsultaGeneral consulta = new ConsultaGeneral(TARIFA);
        Vacunacion vacuna = new Vacunacion(TARIFA, TARIFA);
        Cirugia cirugia = new Cirugia(1.5, 2, TARIFA);
        for (BigDecimal invalido : new BigDecimal[] {null, new BigDecimal("-0.001")}) {
            rechazar(() -> new ConsultaGeneral(invalido));
            rechazar(() -> new Vacunacion(invalido, TARIFA));
            rechazar(() -> new Vacunacion(TARIFA, invalido));
            rechazar(() -> new Cirugia(1.5, 2, invalido));
            rechazar(() -> consulta.setTarifaBase(invalido));
            rechazar(() -> vacuna.setCostoVacuna(invalido));
            rechazar(() -> vacuna.setTarifaAplicacion(invalido));
            rechazar(() -> cirugia.setTarifaPorHora(invalido));
            comprobar(consulta.getTarifaBase().equals(TARIFA), "Conservar tarifa de consulta");
            comprobar(vacuna.getCostoVacuna().equals(TARIFA), "Conservar costo de vacuna");
            comprobar(vacuna.getTarifaAplicacion().equals(TARIFA), "Conservar aplicación");
            comprobar(cirugia.getTarifaPorHora().equals(TARIFA), "Conservar tarifa de cirugía");
        }
    }

    private static void probarImportesCero() {
        ConsultaGeneral consulta = new ConsultaGeneral(BigDecimal.ZERO);
        Vacunacion vacuna = new Vacunacion(BigDecimal.ZERO, BigDecimal.ZERO);
        Cirugia cirugia = new Cirugia(1, 1, BigDecimal.ZERO);
        for (Facturable servicio : List.of(consulta, vacuna, cirugia)) {
            comprobarCosto(servicio, "0.00");
        }
        consulta.setTarifaBase(TARIFA);
        vacuna.setCostoVacuna(TARIFA);
        vacuna.setTarifaAplicacion(TARIFA);
        cirugia.setTarifaPorHora(TARIFA);
        consulta.setTarifaBase(BigDecimal.ZERO);
        vacuna.setCostoVacuna(BigDecimal.ZERO);
        vacuna.setTarifaAplicacion(BigDecimal.ZERO);
        cirugia.setTarifaPorHora(BigDecimal.ZERO);
        for (Facturable servicio : List.of(consulta, vacuna, cirugia)) {
            comprobarCosto(servicio, "0.00");
        }
    }

    private static void probarFactoresInvalidos() {
        Cirugia cirugia = new Cirugia(1.5, 2, TARIFA);
        for (double invalido : valoresNoPositivos()) {
            rechazar(() -> new Cirugia(invalido, 2, TARIFA));
            rechazar(() -> new Cirugia(1.5, invalido, TARIFA));
            rechazar(() -> cirugia.setComplejidad(invalido));
            rechazar(() -> cirugia.setHorasQuirofano(invalido));
            comprobar(cirugia.getComplejidad() == 1.5, "Conservar complejidad válida");
            comprobar(cirugia.getHorasQuirofano() == 2, "Conservar duración válida");
        }
        cirugia.setComplejidad(0.5);
        cirugia.setHorasQuirofano(0.5);
        comprobarCosto(cirugia, "75.00");
    }

    private static void probarRedondeoYTotal() {
        comprobarCosto(new ConsultaGeneral(new BigDecimal("0.005")), "0.01");
        comprobarCosto(new ConsultaGeneral(new BigDecimal("0.004")), "0.00");
        comprobarCosto(new Vacunacion(new BigDecimal("0.004"),
                new BigDecimal("0.004")), "0.01");
        comprobarCosto(new Cirugia(1.5, 1.5, new BigDecimal("0.005")), "0.01");
        List<Facturable> servicios = List.of(new ConsultaGeneral(TARIFA),
                new Vacunacion(new BigDecimal("200"), new BigDecimal("100")),
                new Cirugia(1.5, 2, new BigDecimal("500")));
        BigDecimal total = BigDecimal.ZERO;
        for (Facturable servicio : servicios) {
            total = total.add(servicio.calcularCosto());
        }
        comprobar(total.equals(new BigDecimal("2100.00")), "Total polimórfico de servicios");
    }

    private static void probarEdadYPeso() {
        Perro perro = crearPerro(2, 3);
        for (int edad : new int[] {0, -1}) {
            rechazar(() -> crearPerro(edad, 3));
            rechazar(() -> perro.setEdad(edad));
            comprobar(perro.getEdad() == 2, "Conservar edad válida");
        }
        for (double peso : valoresNoPositivos()) {
            rechazar(() -> crearPerro(2, peso));
            rechazar(() -> perro.setPeso(peso));
            comprobar(perro.getPeso() == 3, "Conservar peso válido");
        }
        perro.setPeso(0.5);
        comprobar(perro.getPeso() == 0.5, "Aceptar peso positivo fraccionario");
    }

    private static void probarPolimorfismo() {
        Dueno dueno = crearDueno();
        List<Mascota> mascotas = List.of(crearPerro(2, 3),
                new Gato("Luna", 2, 3, dueno, true),
                new AveExotica("Paco", 2, 1, dueno, 10));
        List<Persona> personas = List.of(dueno, crearVeterinario(), crearRecepcionista());
        Set<String> sonidos = new HashSet<>();
        Set<String> roles = new HashSet<>();
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        PrintStream original = System.out;
        try (PrintStream captura = new PrintStream(salida)) {
            System.setOut(captura);
            for (Mascota mascota : mascotas) {
                String sonido = mascota.hacerSonido();
                comprobar(sonido != null && !sonido.isBlank(), "Devolver sonido");
                sonidos.add(sonido);
            }
            for (Persona persona : personas) {
                String rol = persona.describirRol();
                comprobar(rol != null && !rol.isBlank(), "Devolver rol");
                roles.add(rol);
            }
        } finally {
            System.setOut(original);
        }
        comprobar(sonidos.size() == 3 && roles.size() == 3, "Comportamientos distintos por tipo");
        comprobar(salida.size() == 0, "Los métodos devuelven texto sin imprimirlo");
    }

    private static void probarDisponibilidad() {
        Clinica clinica = new Clinica("PetCare");
        Veterinario veterinario = crearVeterinario();
        LocalDateTime mismaFecha = LocalDateTime.parse("2026-11-05T10:00");
        comprobar(clinica.validarDisponibilidad(veterinario, HORARIO), "Agenda vacía disponible");
        rechazar(() -> clinica.validarDisponibilidad(null, HORARIO));
        rechazar(() -> clinica.validarDisponibilidad(veterinario, null));
        for (EstadoCita estado : EstadoCita.values()) {
            clinica.getCitas().clear();
            clinica.getCitas().add(citaEnEstado(veterinario, estado));
            boolean disponible = clinica.validarDisponibilidad(veterinario, mismaFecha);
            comprobar(disponible == (estado != EstadoCita.PROGRAMADA),
                    "Disponibilidad según estado: " + estado);
        }
        comprobar(clinica.validarDisponibilidad(veterinario, HORARIO.plusHours(1)),
                "Otro horario disponible");
        comprobar(clinica.validarDisponibilidad(crearVeterinario(), HORARIO),
                "Otro profesional disponible");
    }

    private static void probarRegistroRecepcion() {
        Clinica clinica = new Clinica("PetCare");
        Veterinario veterinario = crearVeterinario();
        clinica.agregarVeterinario(veterinario);
        Consultorio uno = clinica.crearConsultorio(1);
        Consultorio dos = clinica.crearConsultorio(2);
        Recepcionista recepcionista = crearRecepcionista();
        Cita primera = crearCita(veterinario, HORARIO, uno);
        recepcionista.registrarCita(primera, clinica);
        comprobar(clinica.getCitas().contains(primera), "Recepción guarda en la agenda");
        LocalDateTime mismaFecha = LocalDateTime.parse("2026-11-05T10:00");
        recepcionista.registrarCita(crearCita(veterinario, mismaFecha, dos), clinica);
        comprobar(clinica.getCitas().size() == 1, "Rechazar veterinario duplicado por horario");
        recepcionista.registrarCita(crearCita(crearVeterinario(), mismaFecha, uno), clinica);
        comprobar(clinica.getCitas().size() == 1, "Rechazar consultorio ocupado");
        recepcionista.registrarCita(crearCita(veterinario, HORARIO.plusHours(1), uno), clinica);
        comprobar(clinica.getCitas().size() == 2, "Aceptar cita en otro horario");
        rechazar(() -> recepcionista.registrarCita(null, clinica));
        rechazar(() -> recepcionista.registrarCita(primera, null));
        rechazar(() -> recepcionista.registrarCita(crearCita(veterinario, HORARIO, null), clinica));
        comprobar(clinica.getCitas().size() == 2, "Entradas inválidas no cambian la agenda");
    }

    private static Cita citaEnEstado(Veterinario veterinario, EstadoCita estado) {
        // Se aísla la disponibilidad: atender() y cancelar() pertenecen al siguiente bloque.
        return new Cita(crearPerro(2, 3), veterinario, HORARIO, new ConsultaGeneral(TARIFA)) {
            @Override
            public EstadoCita getEstado() {
                return estado;
            }
        };
    }

    private static Cita crearCita(Veterinario veterinario, LocalDateTime fecha, Consultorio sala) {
        return new Cita(crearPerro(2, 3), veterinario, fecha, new ConsultaGeneral(TARIFA), sala);
    }

    private static Dueno crearDueno() {
        return new Dueno("Ana", "Pérez", "123", "ana@example.com", "Calle 1");
    }

    private static Veterinario crearVeterinario() {
        return new Veterinario("Luis", "Pérez", "123", "luis@example.com", "ABC", "General");
    }

    private static Recepcionista crearRecepcionista() {
        return new Recepcionista("Eva", "Pérez", "123", "eva@example.com", "Matutino");
    }

    private static Perro crearPerro(int edad, double peso) {
        return new Perro("Sol", edad, peso, crearDueno(), "Mestizo", "Alta");
    }

    private static double[] valoresNoPositivos() {
        return new double[] {0, -1, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
    }

    private static void comprobarCosto(Facturable servicio, String esperado) {
        comprobar(new BigDecimal(esperado).equals(servicio.calcularCosto()),
                "Importe y escala esperados: " + esperado);
    }

    private static void rechazar(Runnable operacion) {
        try {
            operacion.run();
        } catch (IllegalArgumentException excepcion) {
            comprobaciones++;
            return;
        }
        throw new AssertionError("Se esperaba IllegalArgumentException");
    }

    private static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
        comprobaciones++;
    }
}
