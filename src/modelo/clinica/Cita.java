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
    private String folio;
    private LocalDateTime fechaHora;
    private EstadoCita estado;
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Servicio servicio;
    private String observaciones;
    private Comprobante comprobante;

    /**
     * Crea una cita programada sin consultorio.
     * @param mascota mascota a atender
     * @param veterinario veterinario a cargo
     * @param fechaHora fecha y hora
     * @param servicio servicio solicitado
     */
    public Cita(Mascota mascota, Veterinario veterinario, LocalDateTime fechaHora,
            Servicio servicio) {
        this(mascota, veterinario, fechaHora, servicio, null);
    }

    /**
     * Crea una cita programada con el consultorio indicado.
     * @param mascota mascota a atender
     * @param veterinario veterinario a cargo
     * @param fechaHora fecha y hora
     * @param servicio servicio solicitado
     * @param consultorio consultorio asignado o {@code null} si no tiene
     */
    public Cita(Mascota mascota, Veterinario veterinario, LocalDateTime fechaHora,
            Servicio servicio, Consultorio consultorio) {
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fechaHora = fechaHora;
        this.servicio = servicio;
        this.consultorio = consultorio;
        this.estado = EstadoCita.PROGRAMADA;
    }

    /**
     * Devuelve el folio.
     * @return folio o {@code null} si no tiene
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Cambia el folio.
     * @param folio nuevo folio
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * Devuelve la fecha y hora.
     * @return fecha y hora programadas
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Cambia la fecha y hora.
     * @param fechaHora nueva fecha y hora
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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
     * Cambia la mascota.
     * @param mascota mascota a atender
     */
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    /**
     * Devuelve el veterinario.
     * @return veterinario a cargo
     */
    public Veterinario getVeterinario() {
        return veterinario;
    }

    /**
     * Cambia el veterinario.
     * @param veterinario veterinario a cargo
     */
    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    /**
     * Devuelve el consultorio.
     * @return consultorio asignado o {@code null} si no tiene
     */
    public Consultorio getConsultorio() {
        return consultorio;
    }

    /**
     * Cambia el consultorio.
     * @param consultorio nuevo consultorio
     */
    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    /**
     * Devuelve el servicio.
     * @return servicio de la cita
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**
     * Cambia el servicio.
     * @param servicio servicio solicitado
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    /**
     * Devuelve las observaciones.
     * @return observaciones o {@code null} si no hay
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Cambia las observaciones.
     * @param observaciones detalles de la atención
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Devuelve el comprobante.
     * @return comprobante o {@code null} si no existe
     */
    public Comprobante getComprobante() {
        return comprobante;
    }

    /**
     * Asigna un consultorio disponible.
     * @param consultorio consultorio elegido
     */
    public void asignarConsultorio(Consultorio consultorio) {
    }

    /**
     * Marca la cita como atendida si no está cancelada.
     */
    public void atender() {
    }

    /**
     * Cancela la cita.
     */
    public void cancelar() {
    }

    /**
     * Registra las observaciones de la consulta.
     * @param observaciones detalles de la atención
     */
    public void registrarObservaciones(String observaciones) {
    }

    /**
     * Genera el comprobante de la cita atendida.
     * @return comprobante de cobro
     */
    public Comprobante generarComprobante() {
        return null;
    }
}
