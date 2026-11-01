package modelo.clinica;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda las consultas de una mascota y se da de baja con ella.
 */
public class HistorialClinico {
    private List<Cita> atenciones;

    /**
     * Crea un historial vacío.
     */
    public HistorialClinico() {
        this(new ArrayList<>());
    }

    /**
     * Crea un historial con las atenciones recibidas.
     * @param atenciones citas de la mascota
     */
    public HistorialClinico(List<Cita> atenciones) {
        this.atenciones = atenciones;
    }

    /**
     * Devuelve las atenciones.
     * @return citas del historial
     */
    public List<Cita> getAtenciones() {
        return atenciones;
    }

    /**
     * Añade una cita atendida al historial.
     * @param cita cita atendida
     */
    public void registrarAtencion(Cita cita) {
    }

    /**
     * Lista las atenciones.
     * @return citas atendidas del historial
     */
    public List<Cita> listarAtenciones() {
        return null;
    }
}
