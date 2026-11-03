package modelo.mascotas;

import modelo.personas.Dueno;

/**
 * Perro con su raza y nivel de energía.
 */
public class Perro extends Mascota {
    private String raza;
    private String nivelEnergia;

    /**
     * Crea un perro con sus datos.
     * @param nombre nombre del perro
     * @param edad edad del perro
     * @param peso peso del perro
     * @param dueno dueño del perro
     * @param raza raza del perro
     * @param nivelEnergia nivel de energía
     */
    public Perro(String nombre, int edad, double peso, Dueno dueno, String raza,
            String nivelEnergia) {
        super(nombre, edad, peso, dueno);
        this.raza = validarTexto(raza, "La raza");
        this.nivelEnergia = validarTexto(nivelEnergia, "El nivel de energía");
    }

    /**
     * Devuelve la raza.
     * @return raza del perro
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Cambia la raza.
     * @param raza nueva raza
     */
    public void setRaza(String raza) {
        this.raza = validarTexto(raza, "La raza");
    }

    /**
     * Devuelve el nivel de energía.
     * @return nivel de energía
     */
    public String getNivelEnergia() {
        return nivelEnergia;
    }

    /**
     * Cambia el nivel de energía.
     * @param nivelEnergia nuevo nivel
     */
    public void setNivelEnergia(String nivelEnergia) {
        this.nivelEnergia = validarTexto(nivelEnergia, "El nivel de energía");
    }

    /**
     * Devuelve el sonido del perro.
     * @return sonido del perro
     */
    @Override
    public String hacerSonido() {
        return "Guau";
    }

    /**
     * Resume los datos del perro.
     * @return datos del perro en texto
     */
    @Override
    public String toString() {
        return "Perro | " + super.toString() + " | Raza: " + raza
                + " | Nivel de energía: " + nivelEnergia;
    }
}
