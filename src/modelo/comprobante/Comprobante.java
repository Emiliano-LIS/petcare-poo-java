package modelo.comprobante;

import java.math.BigDecimal;
import java.math.RoundingMode;

import modelo.clinica.Cita;
import modelo.clinica.EstadoCita;

/**
 * Comprobante de cobro de una cita.
 *
 * Regla 5: el total no puede ser negativo.
 * Regla 6: el cobro debe consultarse por mascota y veterinario.
 */
public class Comprobante {
    // static comparte la numeración entre los comprobantes durante la ejecución.
    private static int contadorComprobantes = 0;
    private final String folio;
    private final BigDecimal total;
    private final Cita cita;

    /**
     * Crea el comprobante y conserva el costo calculado al momento de emitirlo.
     * @param cita cita atendida a cobrar
     * @throws IllegalArgumentException si falta la cita, el servicio o el costo es inválido
     * @throws IllegalStateException si la cita no está atendida
     */
    public Comprobante(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita es obligatoria.");
        }
        if (cita.getEstado() != EstadoCita.ATENDIDA) {
            throw new IllegalStateException("Solo se pueden cobrar citas atendidas.");
        }
        if (cita.getServicio() == null) {
            throw new IllegalArgumentException("La cita debe tener un servicio.");
        }

        BigDecimal costo = cita.getServicio().calcularCosto();
        if (costo == null || costo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El costo debe ser un importe no negativo.");
        }

        this.cita = cita;
        this.total = costo.setScale(2, RoundingMode.HALF_UP);
        this.folio = "COMP-" + (++contadorComprobantes);
    }

    /**
     * Devuelve el folio.
     * @return folio del comprobante
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Devuelve el total.
     * @return total del cobro
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Devuelve la cita.
     * @return cita del cobro
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * Prepara el texto del comprobante.
     * @return comprobante en texto
     */
    public String imprimir() {
        return "=== Comprobante de cobro ===\n"
                + "Folio: " + folio + "\n"
                + "Cita: " + cita.getFolio() + "\n"
                // Se uso toPlainString dado que es BigDecimal (Desiciones del pasado :>)
                + "Total: " + total.toPlainString();
    }

    /**
     * Resume el comprobante y el folio de su cita.
     * @return datos del comprobante en texto
     */
    @Override
    public String toString() {
        return "Comprobante: " + folio + " | Cita: " + cita.getFolio()
                + " | Total: $" + total.toPlainString();
    }
}
