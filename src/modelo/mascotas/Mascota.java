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
    private final Dueno dueno;
    private HistorialClinico historial;

    /**
     * Crea la mascota y su historial.
     * @param nombre nombre de la mascota
     * @param edad edad mayor que cero
     * @param peso peso mayor que cero
     * @param dueno dueño de la mascota
     */
    public Mascota(String nombre, int edad, double peso, Dueno dueno) {
        // Usamos throw porqué se pide los errores en este formato.
        if (dueno == null) {
            throw new IllegalArgumentException("El dueño es obligatorio.");
        }
        this.nombre = validarTexto(nombre, "El nombre");
        // Se reutiliza la validación sin llamar métodos sobreescribibles durante la construcción.
        this.edad = validarEdad(edad);
        this.peso = validarPeso(peso);
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
        this.nombre = validarTexto(nombre, "El nombre");
    }

    // Es estatico porqué solo es utilitario, no depende de la instanciación :)
    protected static String validarTexto(String texto, String campo) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(campo + " es obligatorio.");
        }
        // Concatena
        return texto.trim();
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
        this.edad = validarEdad(edad);
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
     * @param peso peso finito mayor que cero
     */
    public void setPeso(double peso) {
        this.peso = validarPeso(peso);
    }

    private static int validarEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor que cero.");
        }
        return edad;
    }

    private static double validarPeso(double peso) {
        if (!Double.isFinite(peso) || peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser finito y mayor que cero.");
        }
        return peso;
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
    /**
     * Devuelve el historial.
     * @return historial de la mascota o null después de su baja
     */
    public HistorialClinico getHistorial() {
        return historial;
    }

    /**
     * Crea un historial vacío al volver a registrar una mascota dada de baja.
     * Conserva el historial existente si la mascota ya tiene uno.
     */
    public void crearHistorial() {
        if (historial == null) {
            historial = new HistorialClinico();
        }
    }

    /**
     * Retira el historial de la mascota durante su baja de la clínica.
     */
    public void eliminarHistorial() {
        historial = null;
        // EL historial NO se elimina de memoria, puede ser recuperado.
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
        return "Nombre: " + nombre + " | Edad: " + edad + " | Peso: " + peso
                + " | Dueño: " + dueno.getNombre() + " " + dueno.getApellido();
    }
}
