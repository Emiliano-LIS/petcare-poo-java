package modelo.clinica;

/**
 * Estados de una cita.
 */
public enum EstadoCita {
    /** Cita programada para atención. */
    PROGRAMADA,
    /** Cita ya atendida. */
    ATENDIDA,
    /** Cita cancelada; no puede pasar a atendida. */
    CANCELADA
}
