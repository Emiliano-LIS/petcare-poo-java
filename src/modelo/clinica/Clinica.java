package modelo.clinica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import modelo.comprobante.Comprobante;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;

/**
 * Organiza los consultorios, veterinarios y citas.
 *
 * Regla 2: un veterinario no puede tener dos citas programadas a la misma hora.
 * Regla 4: un consultorio no puede usarse en dos citas al mismo tiempo.
 * Regla 6: los cobros deben consultarse por mascota y veterinario.
 * Los consultorios pertenecen a la clínica; los veterinarios son independientes.
 */
public class Clinica {
    private String nombre;
    private List<Consultorio> consultorios;
    private List<Veterinario> veterinarios;
    private List<Cita> citas;

    /**
     * Crea una clínica con listas vacías.
     * @param nombre nombre de la clínica
     */
    public Clinica(String nombre) {
        this.nombre = nombre;
        this.consultorios = new ArrayList<>();
        this.veterinarios = new ArrayList<>();
        this.citas = new ArrayList<>();
    }

    /**
     * Devuelve el nombre.
     * @return nombre de la clínica
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre.
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve los consultorios.
     * @return lista de consultorios
     */
    public List<Consultorio> getConsultorios() {
        return consultorios;
    }

    /**
     * Devuelve los veterinarios.
     * @return equipo de la clínica
     */
    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    /**
     * Devuelve las citas.
     * @return citas registradas
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Crea un consultorio de la clínica.
     * @param numero número del consultorio
     * @return consultorio creado
     */
    public Consultorio crearConsultorio(int numero) {
        return null;
    }

    /**
     * Agrega un veterinario al equipo.
     * @param veterinario veterinario ya creado
     */
    public void agregarVeterinario(Veterinario veterinario) {
    }

    /**
     * Agenda una cita si hay disponibilidad.
     * @param cita cita a agendar
     */
    public void agendarCita(Cita cita) {
    }

    /**
     * Busca una cita por folio.
     * @param folio folio a buscar
     * @return cita encontrada o {@code null} si no existe
     */
    public Cita buscarCita(String folio) {
        return null;
    }

    /**
     * Busca las citas de un día.
     * @param fecha día a consultar
     * @return citas de la fecha indicada
     */
    public List<Cita> buscarCita(LocalDate fecha) {
        return null;
    }

    /**
     * Comprueba si el veterinario está disponible.
     * @param veterinario veterinario a consultar
     * @param fechaHora horario propuesto
     * @return {@code true} si el horario está disponible
     */
    public boolean validarDisponibilidad(Veterinario veterinario, LocalDateTime fechaHora) {
        return false;
    }

    /**
     * Consulta el total cobrado por veterinario.
     * @param veterinario veterinario a consultar
     * @return total cobrado por el veterinario
     */
    public BigDecimal consultarTotalCobradoPorVeterinario(Veterinario veterinario) {
        return null;
    }

    /**
     * Consulta los cobros de una mascota.
     * @param mascota mascota a consultar
     * @return comprobantes de la mascota
     */
    public List<Comprobante> consultarCobrosPorMascota(Mascota mascota) {
        return null;
    }
}
