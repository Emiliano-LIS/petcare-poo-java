package modelo.personas;

import modelo.clinica.Cita;
import modelo.clinica.Clinica;

/**
 * Recepcionista que registra las citas.
 *
 * Regla 2: un veterinario no puede tener dos citas programadas a la misma hora.
 * Regla 4: un consultorio no puede usarse en dos citas al mismo tiempo.
 */
public class Recepcionista extends Persona {
    private String turno;

    /**
     * Crea un recepcionista con su turno.
     * @param nombre nombre del recepcionista
     * @param apellido apellido del recepcionista
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param turno turno de trabajo
     */
    public Recepcionista(String nombre, String apellido, String telefono, String correo,
            String turno) {
        super(nombre, apellido, telefono, correo);
        this.turno = turno;
    }

    /**
     * Devuelve el turno.
     * @return turno del recepcionista
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Cambia el turno.
     * @param turno nuevo turno
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * Registra una cita en la clínica, respetando las comprobaciones de su agenda.
     * @param cita cita a registrar
     * @param clinica clínica que administra la agenda
     */
    public void registrarCita(Cita cita, Clinica clinica) {
        if (cita == null || clinica == null) {
            throw new IllegalArgumentException("La cita y la clínica son obligatorias.");
        }
        if (cita.getVeterinario() == null || cita.getFechaHora() == null
                || cita.getMascota() == null || cita.getServicio() == null
                || cita.getConsultorio() == null) {
            throw new IllegalArgumentException("La cita debe tener todos sus datos de atención.");
        }
        clinica.agendarCita(cita);
    }

    /**
     * Describe el rol del recepcionista.
     * @return descripción de su rol
     */
    @Override
    public String describirRol() {
        return "El recepcionista es responsable de gestionar el registro y la agenda de citas.";
    }

    /**
     * Resume los datos del recepcionista.
     * @return datos del recepcionista en texto
     */
    @Override
    public String toString() {
        return "Recepcionista{persona=" + super.toString() + ", turno='" + turno + "'}";
    }
}
