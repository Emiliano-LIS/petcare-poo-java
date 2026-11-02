package modelo.servicios;

import java.math.BigDecimal;

/**
 * Vacunación: costo de la vacuna más su aplicación.
 *
 * Regla 5: vacuna, aplicación y total no pueden tener costos negativos.
 */
public class Vacunacion extends Servicio implements Facturable {
    private BigDecimal costoVacuna;
    private BigDecimal tarifaAplicacion;

    /**
     * Crea una vacunación con sus costos.
     * @param costoVacuna costo no negativo de la vacuna
     * @param tarifaAplicacion tarifa no negativa de aplicación
     */
    public Vacunacion(BigDecimal costoVacuna, BigDecimal tarifaAplicacion) {
        super("Vacunación");
        this.costoVacuna = validarImporte(costoVacuna, "El costo de la vacuna");
        this.tarifaAplicacion = validarImporte(tarifaAplicacion, "La tarifa de aplicación");
    }

    /**
     * Devuelve el costo de la vacuna.
     * @return costo de la vacuna
     */
    public BigDecimal getCostoVacuna() {
        return costoVacuna;
    }

    /**
     * Cambia el costo de la vacuna.
     * @param costoVacuna costo no negativo
     */
    public void setCostoVacuna(BigDecimal costoVacuna) {
        this.costoVacuna = validarImporte(costoVacuna, "El costo de la vacuna");
    }

    /**
     * Devuelve la tarifa de aplicación.
     * @return tarifa de aplicación
     */
    public BigDecimal getTarifaAplicacion() {
        return tarifaAplicacion;
    }

    /**
     * Cambia la tarifa de aplicación.
     * @param tarifaAplicacion tarifa no negativa
     */
    public void setTarifaAplicacion(BigDecimal tarifaAplicacion) {
        this.tarifaAplicacion = validarImporte(tarifaAplicacion, "La tarifa de aplicación");
    }

    /**
     * Suma vacuna y aplicación; redondea el resultado a dos decimales con HALF_UP.
     * @return costo de la vacunación
     */
    @Override
    public BigDecimal calcularCosto() {
        return redondearCosto(costoVacuna.add(tarifaAplicacion));
    }

    /**
     * Resume los datos de la vacunación.
     * @return datos de la vacunación en texto
     */
    @Override
    public String toString() {
        return "Vacunacion{nombre='" + nombre + "', costoVacuna=" + costoVacuna
                + ", tarifaAplicacion=" + tarifaAplicacion + "}";
    }
}
