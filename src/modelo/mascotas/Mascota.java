package modelo.mascotas;

import modelo.clinica.HistorialClinico;
import modelo.personas.Dueno;

/**
 * Datos básicos de una mascota y su historial.
 *
 * Regla 1: edad y peso deben ser mayores que cero.
 * Regla 6: sus cobros deben poder consultarse en Clinica.
 */
public abstract class Mascota {
    /** Nombre de la mascota. */
    protected String nombre;
    private int edad;
    private double peso;
    private Dueno dueno;
    private HistorialClinico historial;

    /**
     * Crea la mascota y su historial.
     * @param nombre nombre de la mascota
     * @param edad edad mayor que cero
     * @param peso peso mayor que cero
     * @param dueno dueño de la mascota
     */
    public Mascota(String nombre, int edad, double peso, Dueno dueno) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.dueno = dueno;
        // El historial es propio de esta mascota; no se comparte.
        this.historial = new HistorialClinico();
    }

    /**
     * Devuelve el nombre.
     * @return nombre de la mascota
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
     * Devuelve la edad.
     * @return edad de la mascota
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Cambia la edad.
     * @param edad edad mayor que cero
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Devuelve el peso.
     * @return peso de la mascota
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Cambia el peso.
     * @param peso peso mayor que cero
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Devuelve el dueño.
     * @return dueño de la mascota
     */
    public Dueno getDueno() {
        return dueno;
    }

    /**
     * Cambia el dueño.
     * @param dueno nuevo dueño
     */
    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    /**
     * Devuelve el historial.
     * @return historial de la mascota
     */
    public HistorialClinico getHistorial() {
        return historial;
    }

    /**
     * Define el sonido según el tipo de mascota.
     * @return sonido de la mascota
     */
    public abstract String hacerSonido();

    /**
     * Resume los datos de la mascota.
     * @return datos de la mascota en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
