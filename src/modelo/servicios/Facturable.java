package modelo.servicios;


/**
 * Se usa java.math.BigDecimal debido a que es más fácil manipularlo que un double (Es un tema de precisión, big decimal permite representarlos exactamente)
 */
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
