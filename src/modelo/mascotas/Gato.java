package modelo.mascotas;

import modelo.personas.Dueno;

/**
 * Datos de un gato, incluido si es doméstico.
 */
public class Gato extends Mascota {
    private boolean domestico;

    /**
     * Crea un gato con sus datos.
     * @param nombre nombre del gato
     * @param edad edad del gato
     * @param peso peso del gato
     * @param dueno dueño del gato
     * @param domestico si el gato es doméstico
     */
    public Gato(String nombre, int edad, double peso, Dueno dueno, boolean domestico) {
        super(nombre, edad, peso, dueno);
        this.domestico = domestico;
    }

    /**
     * Indica si el gato es doméstico.
     * @return {@code true} si el gato es doméstico
     */
    public boolean isDomestico() {
        return domestico;
    }

    /**
     * Define si el gato es doméstico.
     * @param domestico si el gato es doméstico
     */
    public void setDomestico(boolean domestico) {
        this.domestico = domestico;
    }

    /**
     * Devuelve el sonido del gato.
     * @return sonido del gato
     */
    @Override
    public String hacerSonido() {
        return null;
    }

    /**
     * Resume los datos del gato.
     * @return datos del gato en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
