package modelo.servicios;

import java.math.BigDecimal;

/**
 * Consulta veterinaria con tarifa base.
 *
 * Regla 5: tarifa y costo no pueden ser negativos.
 */
public class ConsultaGeneral extends Servicio implements Facturable {
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
        this.tarifaBase = tarifaBase;
    }

    /**
     * Calcula el costo con la tarifa base.
     * @return costo de la consulta
     */
    @Override
    public BigDecimal calcularCosto() {
        return null;
    }

    /**
     * Resume los datos de la consulta.
     * @return datos de la consulta en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
