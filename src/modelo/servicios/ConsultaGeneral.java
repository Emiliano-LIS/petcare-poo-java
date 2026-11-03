package modelo.servicios;

import java.math.BigDecimal;

/**
 * Consulta veterinaria con tarifa base.
 *
 * Regla 5: tarifa y costo no pueden ser negativos.
 */
public class ConsultaGeneral extends Servicio implements Facturable {
    /**
     * Se usa java.math.BigDecimal debido a que es más fácil manipularlo que un double (Es un tema de precisión, big decimal permite representarlos exactamente)
     */
    private BigDecimal tarifaBase;

    /**
     * Crea una consulta con su tarifa.
     * @param tarifaBase tarifa no negativa
     */
    public ConsultaGeneral(BigDecimal tarifaBase) {
        super("Consulta general");
        this.tarifaBase = tarifaBase;
    }

    /**
     * Devuelve la tarifa base.
     * @return tarifa de la consulta
     */
    public BigDecimal getTarifaBase() {
        return tarifaBase;
    }

    /**
     * Cambia la tarifa.
     * @param tarifaBase tarifa no negativa
     */
    public void setTarifaBase(BigDecimal tarifaBase) {
        if (tarifaBase.compareTo(BigDecimal.ZERO) <= 0) {
            /**
             * Aquí se uso el throw para tirar un error a consola de que no se puede asignar una tarifa base negativa o en cero.
             * */
            throw new IllegalArgumentException( "La tarifa base debe ser mayor a cero.");
        }
        this.tarifaBase = tarifaBase;
    }

    /**
     * Calcula el costo con la tarifa base.
     * @return costo de la consulta
     */
    @Override
    public BigDecimal calcularCosto() {
        return tarifaBase;
    }

    /**
     * Resume los datos de la consulta.
     * @return datos de la consulta en texto
     */
    @Override
    public String toString() {
        return "ConsultaGeneral{nombre='" + nombre + "', tarifaBase=" + tarifaBase + "}";
    }
}
