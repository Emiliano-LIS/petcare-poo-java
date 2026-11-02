package modelo.servicios;

import java.math.BigDecimal;

/**
 * Cirugía con complejidad, duración y tarifa por hora.
 *
 * Regla 5: tarifa y costo no pueden ser negativos.
 * La complejidad es un factor positivo y finito: 1 mantiene el precio y 1.5 lo aumenta 50 %.
 * Las horas deben ser positivas y finitas. Solo el costo final se redondea con HALF_UP.
 */
public class Cirugia extends Servicio implements Facturable {
    private double complejidad;
    private double horasQuirofano;
    private BigDecimal tarifaPorHora;

    /**
     * Crea una cirugía con sus datos de cobro.
     * @param complejidad factor multiplicador positivo y finito
     * @param horasQuirofano duración positiva y finita en horas
     * @param tarifaPorHora tarifa no negativa por hora
     */
    public Cirugia(double complejidad, double horasQuirofano, BigDecimal tarifaPorHora) {
        super("Cirugía");
        this.complejidad = validarPositivo(complejidad, "La complejidad");
        this.horasQuirofano = validarPositivo(horasQuirofano, "Las horas de quirófano");
        this.tarifaPorHora = validarImporte(tarifaPorHora, "La tarifa por hora");
    }

    /**
     * Devuelve la complejidad.
     * @return nivel de dificultad
     */
    public double getComplejidad() {
        return complejidad;
    }

    /**
     * Cambia la complejidad.
     * @param complejidad factor multiplicador positivo y finito
     */
    public void setComplejidad(double complejidad) {
        this.complejidad = validarPositivo(complejidad, "La complejidad");
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
     * @param horasQuirofano duración positiva y finita en horas
     */
    public void setHorasQuirofano(double horasQuirofano) {
        this.horasQuirofano = validarPositivo(horasQuirofano, "Las horas de quirófano");
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
        this.tarifaPorHora = validarImporte(tarifaPorHora, "La tarifa por hora");
    }

    private static double validarPositivo(double valor, String campo) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser un número finito mayor que cero.");
        }
        return valor;
    }

    /**
     * Multiplica tarifa, horas y complejidad; redondea a dos decimales con HALF_UP.
     * @return costo de la cirugía
     */
    @Override
    public BigDecimal calcularCosto() {
        // Se convierten los factores a BigDecimal antes de multiplicar para conservar precisión.
        BigDecimal resultado = tarifaPorHora
                .multiply(BigDecimal.valueOf(horasQuirofano))
                .multiply(BigDecimal.valueOf(complejidad));

        return redondearCosto(resultado);
    }

    /**
     * Resume los datos de la cirugía.
     * @return datos de la cirugía en texto
     */
    @Override
    public String toString() {
        return "Cirugia{nombre='" + nombre + "', complejidad='" + complejidad
                + "', horasQuirofano=" + horasQuirofano + ", tarifaPorHora=" + tarifaPorHora + "}";
    }
}
