package modelo.servicios;

import java.math.BigDecimal;

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

    /**
     * Resume los datos del servicio.
     * @return datos del servicio en texto
     */
    @Override
    public String toString() {
        return "Servicio{nombre='" + nombre + "'}";
    }
}
