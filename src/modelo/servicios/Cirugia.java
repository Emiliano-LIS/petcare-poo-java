package modelo.servicios;

import java.math.BigDecimal;

/**
 * Cirugía con complejidad, duración y tarifa por hora.
 *
 * Regla 5: tarifa y costo no pueden ser negativos.
 */
public class Cirugia extends Servicio implements Facturable {
    private String complejidad;
    private double horasQuirofano;
    private BigDecimal tarifaPorHora;

    /**
     * Crea una cirugía con sus datos de cobro.
     * @param complejidad nivel de dificultad
     * @param horasQuirofano horas de quirófano
     * @param tarifaPorHora tarifa no negativa por hora
     */
    public Cirugia(String complejidad, double horasQuirofano, BigDecimal tarifaPorHora) {
        super("Cirugía");
        this.complejidad = complejidad;
        this.horasQuirofano = horasQuirofano;
        this.tarifaPorHora = tarifaPorHora;
    }

    /**
     * Devuelve la complejidad.
     * @return nivel de dificultad
     */
    public String getComplejidad() {
        return complejidad;
    }

    /**
     * Cambia la complejidad.
     * @param complejidad nivel de dificultad
     */
    public void setComplejidad(String complejidad) {
        this.complejidad = complejidad;
    }

    /**
     * Devuelve las horas de quirófano.
     * @return duración en horas
     */
    public double getHorasQuirofano() {
        return horasQuirofano;
    }

    /**
     * Cambia las horas de quirófano.
     * @param horasQuirofano duración en horas
     */
    public void setHorasQuirofano(double horasQuirofano) {
        this.horasQuirofano = horasQuirofano;
    }

    /**
     * Devuelve la tarifa por hora.
     * @return tarifa por hora
     */
    public BigDecimal getTarifaPorHora() {
        return tarifaPorHora;
    }

    /**
     * Cambia la tarifa.
     * @param tarifaPorHora tarifa no negativa por hora
     */
    public void setTarifaPorHora(BigDecimal tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    /**
     * Calcula el costo según complejidad y horas.
     * @return costo de la cirugía
     */
    @Override
    public BigDecimal calcularCosto() {
        return null;
    }

    /**
     * Resume los datos de la cirugía.
     * @return datos de la cirugía en texto
     */
    @Override
    public String toString() {
        return null;
    }
}
