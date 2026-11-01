package modelo.clinica;

/**
 * Espacio numerado para atender mascotas.
 *
 * Regla 4: no puede usarse en dos citas al mismo tiempo.
 * Pertenece a la clínica y se da de baja con ella.
 */
public class Consultorio {
    private int numero;

    /**
     * Crea un consultorio numerado.
     * @param numero número del consultorio
     */
    public Consultorio(int numero) {
        this.numero = numero;
    }

    /**
     * Devuelve el número.
     * @return número del consultorio
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Cambia el número.
     * @param numero nuevo número
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }
}
