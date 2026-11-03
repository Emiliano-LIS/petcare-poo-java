package modelo.clinica;

/**
 * Espacio numerado para atender mascotas.
 *
 * Regla 4: no puede usarse en dos citas al mismo tiempo.
 * Pertenece a la clínica y se da de baja con ella.
 */
public class Consultorio {
    private final int numero;

    /**
     * Crea un consultorio numerado.
     * @param numero número del consultorio
     */
    Consultorio(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "El número del consultorio debe ser mayor que cero.");
        }
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
    /**
     * Resume los datos del consultorio.
     * @return datos del consultorio en texto
     */
    @Override
    public String toString() {
        return "Consultorio " + numero;
    }
}
