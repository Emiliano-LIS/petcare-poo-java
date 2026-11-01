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
        this.costoVacuna = costoVacuna;
        this.tarifaAplicacion = tarifaAplicacion;
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
        this.costoVacuna = costoVacuna;
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
        this.tarifaAplicacion = tarifaAplicacion;
    }

    /**
     * Suma el costo de la vacuna y su aplicación.
     * @return costo de la vacunación
     */
    @Override
    public BigDecimal calcularCosto() {
        return null;
    }

    /**
     * Resume los datos de la vacunación.
     * @return datos de la vacunación en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
