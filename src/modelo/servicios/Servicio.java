package modelo.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Base de los servicios de la clínica.
 *
 * Regla 5: ningún costo puede ser negativo.
 */
public abstract class Servicio implements Facturable {
    /** Nombre del servicio. */
    protected String nombre;

    /**
     * Guarda el nombre del servicio.
     * @param nombre nombre del servicio
     */
    public Servicio(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre.
     * @return nombre del servicio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Acceso de paquete: estas validaciones se comparten solo entre los servicios.
    static BigDecimal validarImporte(BigDecimal importe, String campo) {
        if (importe == null || importe.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(campo + " debe ser un importe no negativo.");
        }
        return importe;
    }

    static BigDecimal redondearCosto(BigDecimal costo) {
        return costo.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Resume los datos del servicio.
     * @return datos del servicio en texto
     */
    @Override
    public String toString() {
        return "Servicio{nombre='" + nombre + "'}";
    }
}
