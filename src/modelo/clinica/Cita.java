package modelo.clinica;

import java.time.LocalDateTime;

import modelo.comprobante.Comprobante;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Servicio;

/**
 * Cita de una mascota con un veterinario.
 *
 * Regla 2: el veterinario no puede tener dos citas programadas a la misma hora.
 * Regla 3: una cita cancelada no puede pasar a atendida.
 * Regla 4: el consultorio no puede usarse en dos citas al mismo tiempo.
 * Regla 6: los cobros deben consultarse por mascota y veterinario.
 */
public class Cita {
    // static comparte la numeración entre todas las citas mientras el programa está en ejecución.
    private static int contadorCitas = 0;
    private final String folio;
    private final LocalDateTime fechaHora;
    private EstadoCita estado;
    private final Mascota mascota;
    private final Veterinario veterinario;
    private final Consultorio consultorio;
    private final Servicio servicio;
    private String observaciones;
    private Comprobante comprobante;

    /**
     * Crea una cita programada sin consultorio.
     * @param mascota mascota a atender
     * @param veterinario veterinario a cargo
     * @param fechaHora fecha y hora
     * @param servicio servicio solicitado
     */
    /**
     * Crea una cita programada con el consultorio indicado.
     * @param mascota mascota a atender
     * @param veterinario veterinario a cargo
     * @param fechaHora fecha y hora
     * @param servicio servicio solicitado
     * @param consultorio consultorio asignado o null si no tiene
     */
    public Cita(Mascota mascota, Veterinario veterinario, LocalDateTime fechaHora, Servicio servicio, Consultorio consultorio) {
        // Lo hacemos en el constructor para evitar inconsistencias futuras.
        if (mascota == null || veterinario == null || fechaHora == null || servicio == null || consultorio == null) {
            throw new IllegalArgumentException("La cita debe tener todos sus datos de atención.");
        }
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fechaHora = fechaHora;
        this.servicio = servicio;
        this.consultorio = consultorio;
        this.estado = EstadoCita.PROGRAMADA;
        this.folio = "CITA-" + (++contadorCitas);
    }

    /**
     * Devuelve el folio.
     * @return folio asignado automáticamente al crear la cita
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Devuelve la fecha y hora.
     * @return fecha y hora programadas
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }


    /**
     * Devuelve el estado.
     * @return estado actual
     */
    public EstadoCita getEstado() {
        return estado;
    }

    /**
     * Devuelve la mascota.
     * @return mascota a atender
     */
    public Mascota getMascota() {
        return mascota;
    }

    /**
     * Devuelve el veterinario.
     * @return veterinario a cargo
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /**
     * Devuelve el consultorio.
     * @return consultorio asignado o null si no tiene
     */
    public Consultorio getConsultorio() {
        return consultorio;
    }

    /**
     * Devuelve el servicio.
     * @return servicio de la cita
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Devuelve las observaciones.
     * @return observaciones o null si no hay
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Devuelve el comprobante.
     * @return comprobante o null si no existe
     */
    public Comprobante getComprobante() {
        return comprobante;
    }

    /**
     * Marca la cita como atendida si no está cancelada.
     */
    public void atender() {
        if (estado != EstadoCita.PROGRAMADA) {
            System.out.println("Solo se puede atender una cita programada");
            return;
        }

        if (mascota.getHistorial() == null) {
            System.out.println("La mascota está dada de baja y no tiene historial activo.");
            return;
        }

        estado = EstadoCita.ATENDIDA;
        mascota.getHistorial().registrarAtencion(this);
    }

    /**
     * Cancela la cita.
     */
    public void cancelar() {
        if (estado != EstadoCita.PROGRAMADA) {
            System.out.println("Solo se puede cancelar una cita programada");
            return;
        }

        estado = EstadoCita.CANCELADA;
    }

    /**
     * Registra las observaciones de la consulta.
     * @param observaciones detalles de la atención
     */
    public void registrarObservaciones(String observaciones) {
        if (estado != EstadoCita.ATENDIDA) {
            System.out.println("Solo se puede registrar observaciones de una cita ya atendida");
            return;
        }

        if (observaciones == null || observaciones.isBlank()) {
            System.out.println("Las observaciones no deben de estar vacias");
            return;
        }

        // Se concatenan.
        this.observaciones = this.observaciones == null ? observaciones.trim()
                : this.observaciones + System.lineSeparator() + observaciones.trim();
    }

    /**
     * Genera el comprobante de la cita atendida.
     * @return comprobante de cobro
     */
    public Comprobante generarComprobante() {
        if (estado != EstadoCita.ATENDIDA) {
            System.out.println("Solo se puede generar el comprobante de una cita atendida.");
            return null;
        }

        if (comprobante == null) {
            // Como comprobante se construye a partir de una Cita cita en su constructor, le pasamos ESTA cita.
            comprobante = new Comprobante(this);
        }

        return comprobante;
    }

    /**
     * Resume la cita mediante los datos principales de sus participantes.
     * @return datos de la cita en texto
     */
    @Override
    public String toString() {
        String estadoVisible = switch (estado) {
            case PROGRAMADA -> "Programada";
            case ATENDIDA -> "Atendida";
            case CANCELADA -> "Cancelada";
        };
        String notas = observaciones == null || observaciones.isBlank()
                ? "Sin observaciones" : observaciones.replaceAll("\\R+", " ");
        return folio + " | Fecha: " + fechaHora.toString().replace('T', ' ')
                + " | Estado: " + estadoVisible + " | Mascota: " + mascota.getNombre()
                + " | Veterinario: " + veterinario.getNombre() + " " + veterinario.getApellido()
                + " | Consultorio: " + consultorio.getNumero()
                + " | Servicio: " + servicio.getNombre() + " | Observaciones: " + notas
                + " | Comprobante: " + (comprobante == null ? "Sin emitir" : comprobante.getFolio());
    }
}
