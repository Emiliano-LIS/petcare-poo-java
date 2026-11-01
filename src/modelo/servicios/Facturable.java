package modelo.servicios;

import java.math.BigDecimal;

/**
 * Permite calcular el costo de un servicio.
 *
 * Regla 5: el costo no puede ser negativo.
 */
public interface Facturable {
    /**
     * Calcula el costo del servicio.
     * @return costo no negativo
     */
    BigDecimal calcularCosto();
}
