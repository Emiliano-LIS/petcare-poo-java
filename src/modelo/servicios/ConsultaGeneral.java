package modelo.servicios;

import java.math.BigDecimal;

/**
 * Consulta veterinaria con tarifa base.
 *
 * Regla 5: tarifa y costo no pueden ser negativos.
 */
public class ConsultaGeneral extends Servicio implements Facturable {
    /**
     * BigDecimal conserva la precisión decimal de los importes monetarios.
     */
    private BigDecimal tarifaBase;

    /**
     * Crea una consulta con su tarifa.
     * @param tarifaBase tarifa no negativa
     */
    public ConsultaGeneral(BigDecimal tarifaBase) {
        super("Consulta general");
        this.tarifaBase = validarImporte(tarifaBase, "La tarifa base");
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
        this.tarifaBase = validarImporte(tarifaBase, "La tarifa base");
    }

    /**
     * Devuelve la tarifa base redondeada a dos decimales con HALF_UP.
     * @return costo de la consulta
     */
    @Override
    public BigDecimal calcularCosto() {
        return redondearCosto(tarifaBase);
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
