package modelo.mascotas;

import modelo.personas.Dueno;

/**
 * Ave exótica con la medida de sus alas.
 */
public class AveExotica extends Mascota {
    private double longitudAlas;

    /**
     * Crea un ave exótica con sus datos.
     * @param nombre nombre del ave
     * @param edad edad del ave
     * @param peso peso del ave
     * @param dueno dueño del ave
     * @param longitudAlas longitud de las alas
     */
    public AveExotica(String nombre, int edad, double peso, Dueno dueno, double longitudAlas) {
        super(nombre, edad, peso, dueno);
        this.longitudAlas = longitudAlas;
    }

    /**
     * Devuelve la longitud de las alas.
     * @return longitud de las alas
     */
    public double getLongitudAlas() {
        return longitudAlas;
    }

    /**
     * Cambia la longitud de las alas.
     * @param longitudAlas nueva longitud
     */
    public void setLongitudAlas(double longitudAlas) {
        this.longitudAlas = longitudAlas;
    }

    /**
     * Devuelve el sonido del ave.
     * @return sonido del ave
     */
    @Override
    public String hacerSonido() {
        return null;
    }

    /**
     * Resume los datos del ave.
     * @return datos del ave en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
