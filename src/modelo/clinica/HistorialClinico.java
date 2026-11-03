package modelo.clinica;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda las consultas de una mascota y se da de baja con ella.
 */
public class HistorialClinico {
    // Es final debido a que siempre será esta lista.
    private final List<Cita> atenciones;

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
        // Muchas validaciones para verificar existencia y consistencia sobre todo.
        if (atenciones == null) {
            throw new IllegalArgumentException("La lista de atenciones es obligatoria.");
        }
        this.atenciones = new ArrayList<>();
        for (Cita cita : atenciones) {
            if (cita == null || cita.getEstado() != EstadoCita.ATENDIDA
                    || this.atenciones.contains(cita)) {
                throw new IllegalArgumentException("Las atenciones deben ser válidas y únicas.");
            }
            if (!this.atenciones.isEmpty()
                    && cita.getMascota() != this.atenciones.get(0).getMascota()) {
                throw new IllegalArgumentException("El historial debe corresponder a una mascota.");
            }
            this.atenciones.add(cita);
        }
    }

    /**
     * Devuelve las atenciones.
     * @return TODAS las citas del historial
     */
    public List<Cita> getAtenciones() {
        // Para evitar problemas de edición (y generar inconsistencias) solo pasamos una copia de la lista
        return List.copyOf(atenciones);
    }

    /**
     * Añade una cita atendida al historial.
     * @param cita cita atendida
     */
    public void registrarAtencion(Cita cita) {
        if (cita == null) {
            System.out.println("La cita debe de contener información");
            return;
        }
        if (cita.getEstado() != EstadoCita.ATENDIDA) {
            System.out.println("La cita debe haber sido atendida para registrarla.");
            return;
        }
        if (cita.getMascota().getHistorial() != this) {
            System.out.println("La cita no pertenece a este historial activo.");
            return;
        }
        if (atenciones.contains(cita)) {
            System.out.println("La atención ya está registrada.");
            return;
        }
        atenciones.add(cita);
    }

    /**
     * Lista las atenciones.
     * @return citas atendidas del historial
     */
    public List<Cita> listarAtenciones() {
        for (Cita cita : atenciones) {
            System.out.println(cita.toString());
        }
        // Para evitar problemas de edición (y generar inconsistencias) solo pasamos una copia de la lista
        return List.copyOf(atenciones);
    }

    /**
     * Resume la cantidad de atenciones del historial.
     * @return resumen del historial en texto
     */
    @Override
    public String toString() {
        // Aca compactamos usando operador ternario (envez de tener un if)
        return "Historial clínico | Atenciones registradas: "
                + (atenciones == null ? 0 : atenciones.size());
    }
}
