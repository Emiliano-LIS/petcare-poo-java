package modelo.comprobante;

import java.math.BigDecimal;

import modelo.clinica.Cita;

/**
 * Comprobante de cobro de una cita.
 *
 * Regla 5: el total no puede ser negativo.
 * Regla 6: el cobro debe consultarse por mascota y veterinario.
 */
public class Comprobante {
    private String folio;
    private BigDecimal total;
    private Cita cita;

    /**
     * Crea el comprobante de una cita.
     * @param cita cita a cobrar
     */
    public Comprobante(Cita cita) {
        this.cita = cita;
    }

    /**
     * Devuelve el folio.
     * @return folio del comprobante
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Cambia el folio.
     * @param folio nuevo folio
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * Devuelve el total.
     * @return total del cobro
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Cambia el total.
     * @param total importe no negativo
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Devuelve la cita.
     * @return cita del cobro
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * Cambia la cita.
     * @param cita cita del cobro
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * Prepara el texto del comprobante.
     * @return comprobante en texto
     */
    public String imprimir() {
        return null;
    }
}
