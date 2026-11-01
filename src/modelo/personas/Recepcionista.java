package modelo.personas;

import modelo.clinica.Cita;

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
     * Registra una cita.
     * @param cita cita a registrar
     */
    public void registrarCita(Cita cita) {
    }

    /**
     * Describe el rol del recepcionista.
     * @return descripción de su rol
     */
    @Override
    public String describirRol() {
        return null;
    }
}
